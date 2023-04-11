<template>
    <el-main>
<div id = "forgetPwd">
    <div>
        <br><br><br><br><br><br><br><br><br>
        <h1>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp{{title}}</h1>
        <br>
        <div v-if="isReset">用户名:&nbsp&nbsp&nbsp&nbsp&nbsp<input v-model = "userName" type = "text"/></div>
        <br>
    </div>
    <div>
        <div v-if="isReset">电子邮箱:&nbsp&nbsp<input v-model = "Email" type = "text"/>&nbsp&nbsp&nbsp&nbsp<button v-if = "isReset" @click = "getVerific" v-show="codeShow">获取验证码</button><button v-show="!codeShow">{{count}}秒后重试</button></div>
        <br>
        <div v-if = "isReset">验证码:&nbsp&nbsp&nbsp&nbsp&nbsp<input v-model = "verification" type = "text"/></div>
        <br>
    </div>
    <div>
        <div v-if="isReset">重置密码:&nbsp&nbsp<input v-model = "password" type = "password"/></div>
        <br>
        <div v-if="isReset">重置密码确认:&nbsp<input v-model = "passwordVerification" type = "password"/></div>
        <br>
        <button v-if="isReset" @click = "resetPwd">{{button1}}</button>
    </div>

</div>
    </el-main>
</template>

<script>
    import axios from "axios";

    export default {
        name: "forgetPwd",
        data(){
            return{
                title:"忘记密码",
                userName:"",
                Email:"",
                password:"",
                passwordVerification:"",
                verification:"",
                conCode:"",
                button1:"重置",
                isReset:true,
                message:"",
                count:"",
                timer:"",
                codeShow:true,
            }
        },
        methods:{
            resetPwd(){
                if(!this.judge()){
                  return;
                }
                  let params = new URLSearchParams()
                  params.append('name',this.userName)
                  axios({
                    method:'post',
                    url:'http://localhost:8081/forgetPwd/checkUser',
                    data:params
                  }).then(res=>{
                    if(!res.data.flag){
                      this.$message(res.data.msg)
                      return;
                    }
                  })
                  params.append('email',this.Email)
                  axios({
                    method:'post',
                    url:'http://localhost:8081/forgetPwd/checkEmail',
                    data:params
                  }).then(res=>{
                    if(!res.data.flag){
                      this.$message(res.data.msg)
                      return;
                    }
                  })
                  params.delete('name')
                  params.append('pwd',this.password)
                  axios({
                    method:'post',
                    url:'http://localhost:8081/forgetPwd/rePwd',
                    data:params
                  }).then(res=>{
                    if(!res.data.flag){
                      this.$message(res.data.msg)
                      return;
                    }
                    this.isReset = false;
                    this.title = "重置成功"
                    this.$message.info("密码重置成功")
                    window.location.href='http://localhost:8080/#/';
                  })
            },
            getVerific(){
              var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
              if(!reg.test(this.Email)){
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
                   email:this.Email
                 }
               }).then(res=>{
                 this.conCode = res.data.data;
                 console.log(this.conCode);
               })
            },

          judge(){
            console.log(this.verification);
            if(this.userName.length === 0){
              this.$message.error("用户名为空")
            }
            else if(this.password.length === 0) {
              this.$message.error("密码为空")
            }
            else if(this.password !== this.passwordVerification) {
              this.$message.error("两次输入密码不一致")
            }
            else if(this.conCode===""){
              this.$message.error("请点击获取验证码")
            }
            else if(this.verificatione===""){
              this.$message.error("请输入验证码")
            }
            else if(this.conCode != this.verification){
              this.$message.error("验证码错误")
            }
            else {
              return true;
            }
            return false;
          },
          parseData(method, url,data){
            if(method === 'post'){
              let param = new URLSearchParams()
              for(let i in data){
                param.append(i,data[i])
              }
              axios({
                method:method,
                url:'http://localhost:8081' + url,
                data:param
              }).then(res=>{
                this.rs = res.data;
              })
            }
            else{
              axios({
                method:method,
                url:'http://localhost:8081' + url,
                params:data

              }).then(res=>{
                this.rs = res.data;
              })
            }
          }
        },
        components:{

        }

    }
</script>

<style scoped>
    #forgetPwd{
        position:absolute;
        top: 30%;
        left: 50%;
        transform: translate(-50%, -50%);
    }

</style>
