
<template>
  <el-main>
    <div class="login_container">
      <div class = "login_box">
        <div class="avatar_box">
          <img src="../assets/2255.jpg" alt=""/>
        </div>
        <br><br><br><br><br>
        <h1 class="dad">博客系统</h1>
        <el-form label-width="0px" class="login_form">
          <!-- 用户名 -->
          <el-form-item>
            <el-input v-model="userName" size="large" type="text" prefix-icon="User"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="password" size="large" type="password" prefix-icon="Lock"></el-input>
          </el-form-item>
          <!-- 按钮区域 -->
          <el-row justify="end">
            <el-form-item class="login_btn">
              <el-button @click="clickLogIn" type="primary">登录</el-button>
              <el-button @click="reset" type="info">注册</el-button>
              <el-button @click="forget" type="info">忘记密码</el-button>
            </el-form-item>
          </el-row>
        </el-form>
      </div>
    </div>
  </el-main>
</template>

<script>
import axios from "axios";
import App from "@/App.vue";
import Color from "element-plus/lib/components/color-picker/src/utils/color";
export default {
  name: "blogLogIn",
  computed: {
    Color() {
      return Color
    }
  },
  components: {App},
  data() {
    return {

      userName: "",
      password: "",
      buttonTitle1: "登录",
      rawHtml1: "<a href='http://localhost:8080/#/about'>注册</a>",
      rawHtml2: "<a href='http://localhost:8080/#/forget'>忘记密码</a>",


    }
  },
  methods:{
    clickLogIn(){
      axios({
        method:'get',
        url:'http://localhost:8081/login',
        params: {
          name:this.userName,
          password:this.password
        }
      }).then(res=>{
        if(res.data.flag){
          this.$message.success(res.data.msg);
          window.location.href='http://localhost:8080/#/';
        }
      })
    },
    reset(){
      window.location.href="http://localhost:8080/#/about";
    },
    forget(){
      window.location.href='http://localhost:8080/#/forget';
    }


  },
}
</script>
<style lang="less" scoped>
.login_container {
  height: 100vh;
}
.login_box {
  // 宽450像素
  width: 600px;
  // 高300像素
  height: 400px;
  // 背景颜色
  background-color: rgba(0,0,0,0.65);
  // 圆角边框3像素
  border-radius: 10px;
  // 绝对定位
  position: absolute;
  // 距离左则50%
  left: 50%;
  // 上面距离50%
  top: 50%;
  // 进行位移，并且在横轴上位移-50%，纵轴也位移-50%
  transform: translate(-50%, -50%);
  .avatar_box {
    // 盒子高度130像素
    height: 130px;
    // 宽度130像素
    width: 130px;
    // 边框线1像素 实线
    border: 1px solid #eee;
    // 圆角
    border-radius: 50%;
    // 内padding
    padding: 10px;
    // 添加阴影 向外扩散10像素 颜色ddd
    box-shadow: 0 0 10px #ddd;
    // 绝对定位
    position: absolute;
    // 距离左则
    left: 50%;
    // 位移
    transform: translate(-50%, -50%);
    // 背景
    background-color: #fff;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}
.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}
.login_btn {
  // 设置弹性布局
  display: flex;
  // 横轴上尾部对齐
  justify-content: flex-end;
}
.el-main{
  background: url("@/assets/4455.jpg") no-repeat center;
/*  top: 0;*/
  left: 0;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
.el-input{
  width: 400px;
  left: 15%;
}
.dad{
  text-align: center;
  color: darkgrey;
}
</style>

