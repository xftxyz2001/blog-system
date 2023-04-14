
<template>
  <el-main>
    <div>
      <div id = "blogLogIn" style = "text-align: center;">
        <br><br><br><br><br><br><br><br><br>
        <h1>{{title}}</h1>
        <br>
        <div>用户名:&nbsp<input v-model = "userName" type = "text"/></div>
        <div>&nbsp</div>
        <div>密码:&nbsp&nbsp&nbsp&nbsp<input v-model = "password" type = "password"/></div>
        <div>&nbsp</div>
        <button @click="clickLogIn">{{buttonTitle1}}</button>
        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
        <span v-html="rawHtml1"></span> |
        <span v-html="rawHtml2"></span>
        <div class="login_fal" v-show="show" v-cloak>用户名或密码出现错误，请重新输入</div>
      </div>
    </div>
  </el-main>
</template>

<script>
import axios from "axios";

export default {
  name: "blogLogIn",
  data() {
    return {

      title: "博客系统登录",
      userName: "",
      password: "",
      buttonTitle1: "登录",
      rawHtml1: "<a href='/#/about'>注册</a>",
      rawHtml2: "<a href='/#/forget'>忘记密码</a>",


    }
  },
  methods:{
    clickLogIn(){
      axios({
        method:'post',
        url:'/login',
        params: {
          name:this.userName,
          password:this.password
        }
      }).then(res=>{
        if(res.data.flag){
          this.$message.success(res.data.msg);
          window.location.href='/#/home';
        }
      })
    }

  }
}
</script>

<style scoped>
</style>
