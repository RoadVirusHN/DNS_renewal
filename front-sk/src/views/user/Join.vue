
<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
  <div>
    <h1>가입하기</h1>
    <div>
      <div>
        <input
          id="nickname"
          v-model="nickName"
          placeholder="닉네임을 입력하세요."
          type="text"
        >
        <label for="nickname">닉네임</label>
      </div>

      <div>
        <input
          id="email"
          v-model="email"
          placeholder="이메일을 입력하세요."
          type="text"
        >
        <label for="email">이메일</label>
      </div>

      <div>
        <input
          id="password"
          v-model="password"
          :type="passwordType"
          placeholder="비밀번호를 입력하세요."
        >
        <label for="password">비밀번호</label>
      </div>

      <div>
        <input
          id="password-confirm"
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          placeholder="비밀번호를 다시한번 입력하세요."
        >
        <label for="password-confirm">비밀번호 확인</label>
      </div>
    </div>

    <label>
      <input
        id="term"
        v-model="isTerm"
        type="checkbox"
      >
      <span>약관을 동의합니다.</span>
    </label>

    <!-- <span @click=" ">약관보기</span> -->

    <button>
      가입하기
    </button>
  </div>
</template>

<script>

    export default {
        data: () => {
            return {
                email: '',
                password: '',
                passwordConfirm: '',
                nickName: '',
                isTerm: false,
                isLoading: false,
                error: {
                    email: false,
                    password: false,
                    nickName: false,
                    passwordConfirm: false,
                    term: false
                },
                isSubmit: false,
                passwordType: "password",
                passwordConfirmType: "password",
                termPopup: false,
            }
        }

    }

</script>


