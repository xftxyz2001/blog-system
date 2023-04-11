<template>
<el-main>
    <div id ="hello">
        <br><br><br><br><br><br><br><br><br><br><br>
        <h1>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注册</h1>
        <br>
    <label>用户名:&nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" v-model.trim="username1"></label>
        <br><br>
    <label>密码:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="password" v-model.trim="password"></label>
    <br><br>
    <label>确认密码:&nbsp<input type="password" v-model.trim="definepassword"></label>
    <br><br>
    <label>邮箱:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" v-model.trim="email">
        <button @click="clickscode" v-if="codeShow">获取验证码</button>
        <button v-if="!codeShow">{{count}}秒后重试</button>
    </label>
<!--      <span v-if="codeShow" class="getcold" @click="clickscode">获取验证码</span>
      <span v-if="!codeShow" class="count">{{count}}秒后重试</span>-->
    <br><br>
   <label>验证码:&nbsp&nbsp&nbsp&nbsp&nbsp<input type="text" v-model.trim="scode"></label>
        <br><br>
        <button @click="clickzhuce">注册</button>
    </div>
    </el-main>
</template>

<script>

    import axios from "axios";

    export default {
        name: "register",
        data(){
            return{
                username1:"",
                password:"",
                definepassword:"",
                email:"",
                scode:"",
                conCode:"",
                /*text:"获取验证码",*/
                codeShow: true, //获取验证码倒计时
                timer: null, //定时器
                count: ''
            }
        },
        methods:{
            clickzhuce() {
              if(!this.judge()){
                return;
              }
              let param = new URLSearchParams()
              param.append('name',this.username1)
              param.append('pwd',this.password)
              param.append('email',this.email)
              axios({
                method:'post',
                url:'http://localhost:8081/register/actReg',
                data:param
              }).then(res=>{
                if(res.data.flag){
                  this.$message.success(res.data.msg);
                  window.location.href='http://localhost:8080/#/';
                }
                else{
                  this.$message.error("邮箱已被使用");
                }
              })
            },

          clickscode(){
            var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if(!reg.test(this.email)){
              this.$message.error("邮箱格式错误")
              return;
            }
            const TIME_COUNT = 60;
            if (!this.timer) {
              this.count = TIME_COUNT;
              this.codeShow = false;
              this.timer = setInterval(() => {
                if (this.count > 0 && this.count <= TIME_COUNT) {
                  this.count--;
                } else {
                  this.codeShow = true;
                  clearInterval(this.timer);
                  this.timer = null;
                }
              }, 1000)
            }
            axios({
              method:'get',
              url:'http://localhost:8081/register/ConfirmCode',
              params:{
                email:this.email
              }
            }).then(res=>{
              this.conCode = res.data.data;
              console.log(this.conCode);
            })
          },

          judge(){
              console.log(this.scode);
              if(this.username1.length === 0){
                this.$message.error("用户名为空")
              }
              else if(this.password.length === 0) {
                this.$message.error("密码为空")
              }
              else if(this.password !== this.definepassword) {
                this.$message.error("两次输入密码不一致")
              }
              else if(this.conCode===""){
                this.$message.error("请点击获取验证码")
              }
              else if(this.scode===""){
                this.$message.error("请输入验证码")
              }
              else if(this.conCode != this.scode){
                this.$message.error("验证码错误")
              }
              else {
                 return true;
              }
              return false;
          }
        }

    }
</script>

<style scoped>
    #hello{
        position:absolute;
        top: 30%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
</style>
