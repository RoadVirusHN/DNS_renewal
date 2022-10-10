<template>
  <div>
    <v-text-field
      v-model="input"
      :error-messages="errors"
      :error="error"
      :append-icon="appendicon"
      :type="show ? 'text':'password'"
      :required="required"
      :hint="hint"
      :label="label"
      :counter="getMaxlength()"
      :rules="getRule()"
      :placeholder="defaultVal"
      :clearable="clearable"
      :loading="loading"
      @click:append="hidedata ? show = !show : ''"
      @input="changeInput"
    />
  </div>
</template>

<script>
/*
    input 버튼 API
    - 문자열을 받는 한줄 짜리 input의 API
    
    (Obsolete) inputValue(String) : 인풋에 넣을 값 (default='')
    
    inputType(String) : 입력 값의 종류
        - 'password' : 8자 이상의 영문자, 특수문자를 섞은 30자 이하의 문자열, 
        - 'email' : 유저이름@사이트이름.구분의 50자 이하의 문자열, 중복 불가
        - 'nickname' : 유저의 이메일 이름 중복 불가
        - 'custom' : 독자적인 룰을 입력해야 하는 문자열
        - default, '' : 룰 없음
    
    maxLength(Number) : 최대 문자열 길이 카운터 생성 (default=undefined(무한대))

    defaultVal(String) : 기본값(placeholder) (default='')
    
    hide(Boolean) : 기본값이 숨김인 숨김 버튼 생성 (default='false')
    
    hint(String) : 아래에 표시될 hint (default='')
    
    required(Boolean) : 버튼을 누르는데 필수적으로 넣어야하는가?(default='false')
    
    label(String) : 라벨명(default='')

    custRule(Array) : inputType이 custom일 경우 입력해야 하는 독자적인 룰
    - 예시 : custRule = nicknameRules
        v는 입력 값
        data property에
        nicknameRules: [
                v => !!v || '닉네임을 적어주세요.',
                v => (v && v.length <= 30) || '닉네임이 너무 깁니다.'
        ],

    enterInput(Function) : 인풋 값이 바뀌었을 때의 이벤트 (보통 값을 가져오는데 사용)

    clearable(Boolean) : 삭제 가능한 버튼을 생성(Default: false)

*/
    import * as EmailValidator from 'email-validator';
    import PV from 'password-validator'
    import http from '../../http-common.js'


    export default {
        name: "InputComponent",
        props : {
            required : {
                type: Boolean,
                default: false,
            },
            // inputValue :{
                //     type: [Number, String],
            //     default: '',
            // },
            maxLength: {
                type: Number,
                default: undefined,
            },
            inputType :{
                type: String,
                default: '',
            },
            hide : {
                type: Boolean,
                default: false,
            },
            defaultVal : {
                type: String,
                default: '',
            },
            hint : {
                type: String,
                default: '',
            },
            label : {
                type: String,
                default: '',
            },
            custRule : {
                type: Array,
                default: ()=> [''],
            },
            // eslint-disable-next-line vue/require-default-prop
            enterInput:{
                type:Function
            },
            clearable:{
                type:Boolean,
                default: false,
            },
        },
        data(){
            return {
                passwordSchema: new PV(),
                input: '',
                errors: [],
                error: false,
                loading: false,
                type:"",
                show: false,
                hidedata: false,
                text:"",
                isDupl: "ini",
                Rules: {
                    emailRules: [
                        v => !!v || '이메일을 적어주세요.',
                        v => !!EmailValidator.validate(v) || '올바른 형식의 이메일이 아닙니다.',
                        v => (v && v.length <= 50) || '이메일이 너무 깁니다.',
                    ],
                    pwRules: [
                        v => !!v || '비밀번호를 적어주세요.',
                        v => (v && v.length <=30 && v.length>= 8)|| '8자 이상, 30자 이하의 비밀번호를 입력하세요.',
                    ],
                }
            }
        }, 
        computed: {
            appendicon(){
                return this.hidedata ? (this.show ? 'mdi-eye' : 'mdi-eye-off') : ''
            },
        },
        watch:{
            input(val){
                if (this.inputType==='email'){
                    if (this.checkEmailValid(val)){       
                        this.isDupl='w' 
                        this.errors=["중복 확인 중"]
                        this.error=true
                        this.loading="warning"
                        http.get('/user/checkduplicateemail/'+val)
                        .then(response => {                
                            this.isDupl=response.data.resvalue    
                            this.loading=false

                            if (this.isDupl===true){
                                    this.error =true
                                    this.errors = ['중복된 이메일입니다.']
                                }
                            else {
                                    this.error=false
                                    this.errors=[]
                            }
                        })
                        .catch()
                                   
                    }
                }
                else if( this.label==='닉네임(*)'){
                    if (this.checkCustomValid(val,this.custRule)){       
                        this.isDupl='w' 
                        this.errors=["중복 확인 중"]
                        this.error=true
                        this.loading="warning"
                        http.get('/user/checkduplicatenickname/'+val)
                        .then(response => {               
                            console.log(response)           
                            this.isDupl=response.data.resvalue    
                            this.loading=false

                            if (this.isDupl===true){
                                    this.error =true
                                    this.errors = [`중복된 ${this.label}입니다.`]
                                }
                            else {
                                    this.error=false
                                    this.errors=[]
                            }
                        })
                        .catch()        
                    }
                }
            }
        },    
        created(){
            this.passwordSchema
                .is().min(8)
                .is().max(100)
                .has().digits()
                .has().letters()
                .has().symbols();

                this.Rules.pwRules.push(v => (v&&this.passwordSchema.validate(v)) || '영문, 숫자, 특수 문자 조합의 비밀번호를 입력해 주세요')
          
        },
        mounted:function(){
            this.hidedata = this.hide
            this.setType()         
        },
        methods: {
            checkEmailValid(v){
                return !!v && !!EmailValidator.validate(v) && v.length <= 50
            },
            checkCustomValid(v, rules){
                let temp = true
                rules.forEach(element => {
                    if (element(v)!==true)
                        temp=false
                });
                if (temp)
                    return true
                else
                    return false
            },
            getMaxlength(){
                if (this.maxLength > 0) return this.maxLength
                else return
            },
            setType(){
                switch (this.inputType) {
                    case 'password':
                        this.hidedata = true
                        this.show = false
                        break;
                    case 'email':
                    case 'custom':
                    default:                 
                        this.show = true
                        if (this.hidedata === true)
                            this.show = false
                        break;
                }
            },
            changeInput(event){
                this.enterInput(event, this.label);
            },
            getRule(){
                if (this.inputType==='password'){                                      
                    return this.Rules.pwRules
                }
                else if (this.inputType==='email'){
                    return this.Rules.emailRules
                }
                return (this.inputType==='custom' && this.custRule ? this.custRule : [])
            },
        },
    }    
</script>