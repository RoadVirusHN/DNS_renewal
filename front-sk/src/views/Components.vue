<template>
  <div class="wrap components-page">
    <div class="wrapB">
      <h1>Style Component</h1>

      <h2>form</h2>
      <div class="wrap">
        <!-- input type=text -->
        <InputComponent
          input-value="email"
          :error-text="error.email"
          :enter-input="enterInput"
          placeholder="이메일을 입력해주세요."
          label="이메일"
        />

        <!-- input type=password -->
        <InputComponent
          input-value="password"
          :error-text="error.password"
          :enter-input="enterInput"
          placeholder="비밀번호를 입력해주세요."
          label="비밀번호"
          password="true"
        />

        <!-- textarea -->
        <TextareaComponent
          input-value="contents"
          placeholder="의견을 적어주세요."
          label="게시하기"
          max-length="300"
        />

        <!-- form -->
        <SelectComponent :options="options" />
        <CheckboxComponent
          label="약관에 동의합니다."
          check-value="agree"
        />
      </div>

      <h2>switch</h2>
      <div class="wrap">
        <SwitchComponent />
      </div>

      <h2>button</h2>
      <div class="wrap">
        <!-- button -->
        <LargeButton
          text="내 큐레이션에 추가"
          :is-background="true"
        />
        <LargeButton
          text="내 큐레이션에 추가"
          :is-background="false"
        />
        <SmallButton
          text="승인"
          :is-background="true"
        />
        <SmallButton
          text="거절"
          :is-background="false"
        />
        <HalfButton :buttons="buttons" />
      </div>

      <h2>header</h2>
      <div class="wrap">
        <!-- header -->
        <HeaderComponent
          header-title="헤더 타이틀"
          :is-search="true"
        />
        <HeaderComponent
          header-title="헤더 타이틀"
          :is-back="true"
        />
        <HeaderComponent
          header-title="헤더 타이틀"
          :is-back="true"
          right-text="저장"
          is-disabled="true"
        />
        <HeaderComponent
          header-title="헤더 타이틀"
          :is-back="true"
          right-text="저장"
        />
        <HeaderComponent
          header-title="헤더 타이틀"
          :is-back="true"
          :is-search="true"
        />
      </div>

      <h2>tab</h2>
      <div class="wrap">
        <!-- tab -->
        <TabComponent tab-title="탭" />
        <TabComponent
          tab-title="탭"
          :is-active="true"
        />
        <TabComponent
          tab-title="탭"
          :is-active="true"
          count="23"
        />
      </div>

      <h2>keyword</h2>
      <div class="wrap">
        <!-- keyword -->
        <KeywordDelComponent keyword-title="서비스" />
        <KeywordAddComponent />
      </div>

      <h2>curation</h2>
      <div class="wrap">
        <!-- curation -->
        <CurationComponent curation-title="내 큐레이션" />
      </div>

      <h2>toast</h2>
      <div class="wrap">
        <!-- toast -->
        <ToastComponent toast-title="스크랩되었습니다." />
        <ToastComponent
          toast-title="500 Error"
          :is-error="true"
        />
        <ToastComponent
          toast-title="스킵되었습니다."
          :is-cancel="true"
        />
      </div>
    </div>
  </div>
</template>

<script>
    import InputComponent from '../components/common/Input'
    import TextareaComponent from '../components/common/Textarea'
    import LargeButton from '../components/common/ButtonLarge'
    import SmallButton from '../components/common/ButtonSmall'
    import HalfButton from '../components/common/ButtonHalf'
    import SelectComponent from '../components/common/Select'
    import CheckboxComponent from '../components/common/Checkbox'
    import SwitchComponent from '../components/common/Switch'
    import HeaderComponent from '../components/common/Header'
    import TabComponent from '../components/common/Tab'
    import KeywordDelComponent from '../components/common/KeywordDel'
    import KeywordAddComponent from '../components/common/KeywordAdd'
    import CurationComponent from '../components/common/Curation'
    import ToastComponent from '../components/common/Toast'
    import '../assets/css/components.scss'

    export default {
        name: "Components",
        components: {
            InputComponent,
            TextareaComponent,
            LargeButton,
            SmallButton,
            HalfButton,
            SelectComponent,
            CheckboxComponent,
            SwitchComponent,
            HeaderComponent,
            TabComponent,
            KeywordDelComponent,
            KeywordAddComponent,
            CurationComponent,
            ToastComponent,
        },
        data: () => {
            return {
                email:"",
                password:"",
                contents:"",
                error: {
                    email: "",
                    password: "",
                },
                buttons: [
                    {
                        title: '확인',
                        highlight: true,
                        onClick: () => {

                        }
                    },
                    {
                        title: '취소',
                        onClick: () => {
                        }
                    }
                ],
                options: [
                    {
                        value:"option1",
                        title:"옵션1",
                    },
                    {
                        value:"option2",
                        title:"옵션2",
                    },
                ]
            }
        },
        methods:{
            enterInput(e,type) {
                if(type==='email') {
                    this.email = e;
                } else if(type==="password") {
                    this.password = e;
                }
            }
        },
    }
</script>

<style scoped>

</style>