<template>
  <el-container class="asd1">
    <el-header style="background-color: rgba(107,195,13,0)">
      <div id="login">
        <Handler id="handler"></Handler>
      </div>
    </el-header>
    <el-main class="main">
      <el-container class="main" >
        <el-header class="selfIntroduction">
          <el-row>
            <el-col span="650px"><el-avatar :size= "100" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></el-col>
            &nbsp&nbsp
            <el-col span="650px" style="font-size: 30px;padding-top:15px;padding-left: 12px">

              {{ userName }}<span style="font-size: 20px">&nbsp&nbsp加入本博客日期:&nbsp{{registerDate}}</span>
              <span class="toolbar">
                                <el-button style="font-size: 20px;text-align: center;background-color: #c9c9c9;" v-if="isMyself" @click="settings">设置</el-button>
                                <el-button style="font-size: 20px;text-align: center;background-color: #c9c9c9;" v-else @click="attention">关注</el-button>
                                <el-dropdown style="justify-content: center;padding-left: 35px">
                                    <el-button style="font-size: 30px;text-align: center;background-color: #c9c9c9;">...</el-button>
                                    <template #dropdown>
                                        <!--这里要进行windows.local与本uid进行比对，若相同显示1菜单，不同说明访问的是别人的主页显示2菜单-->
                                      <!--1菜单-->
                                        <el-dropdown-menu v-if="isMyself">
                                            <el-dropdown-item  @click="a1">编辑资料</el-dropdown-item>
                                            <el-dropdown-item @click="exit">登出系统</el-dropdown-item>
                                        </el-dropdown-menu>
                                        <el-dropdown-menu v-else>
                                            <el-dropdown-item @click="a2">私聊</el-dropdown-item>
                                            <el-dropdown-item>提问</el-dropdown-item>
                                        </el-dropdown-menu>
                                    </template>
                                </el-dropdown>
                            </span>
              <div style="padding-top: 9px">
                                <span style="font-size: 35px">
                                    |&nbsp&nbsp
                                </span>
                <span style="font-size: 25px">原创&nbsp&nbsp:&nbsp&nbsp{{articalNum}}篇</span>
                <span style="font-size: 35px">
                                    &nbsp&nbsp|
                                </span>
                <span style="font-size: 35px">
                                    &nbsp&nbsp
                                </span>
                <span style="font-size: 25px">活跃天数&nbsp&nbsp:&nbsp&nbsp{{activeDay}}天</span>
                <span style="font-size: 35px">
                                    &nbsp&nbsp|
                                </span>
                <span style="font-size: 35px">
                                    &nbsp&nbsp
                                </span>
                <span style="font-size: 25px">粉丝数&nbsp&nbsp:&nbsp&nbsp{{fansNum}}</span>
                <span style="font-size: 35px">
                                    &nbsp&nbsp|
                                </span>
                <span style="font-size: 35px">
                                    &nbsp&nbsp
                                </span>
                <span style="font-size: 25px">总访问量&nbsp&nbsp:&nbsp&nbsp{{watchNum}}</span>
                <span style="font-size: 35px">
                                    &nbsp&nbsp|
                                </span>
              </div>
              <el-row>
                <div style="font-size: 20px;padding-top: 10px">博客简介:&nbsp这是{{userName}}的博客</div>
              </el-row>
            </el-col>
          </el-row>
        </el-header>
        <el-aside width="500px" height="500px">123</el-aside>
        <el-main class="main2">
          <el-container>
            <el-header
                style="background-color: #efe8e8;text-align: center;
                                padding-top: 15px;font-size: 20px;
                                border:3px solid #c9c9c9">
              <el-row gutter="20">
                <el-col :span="6"><div @click="concern">关注</div></el-col>
                <el-col :span="6"><div @click="collect">收藏</div></el-col>
                <el-col :span="6"><div @click="artical">发表文章</div></el-col>
                <el-col :span="6"><div @click="recent">最近</div></el-col>
              </el-row>
            </el-header>
            <!--show[0],关注-->
            <el-main v-if="show[0]">
              <el-row>
                <el-col span="650px"><el-avatar :size= "100" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></el-col>
                &nbsp&nbsp
                <el-col span="650px" style="font-size: 30px;padding-top:15px;padding-left: 12px">

                  {{ userName }}<span style="font-size: 20px">&nbsp&nbsp加入本博客日期:&nbsp{{registerDate}}</span>
                </el-col>
              </el-row>
            </el-main>
            <!--show[1],收藏-->
            <el-main v-if="show[1]">

            </el-main>
            <!--show[2],发表文章-->
            <el-main v-if="show[2]">

            </el-main>
            <!--show[3],最近-->
            <el-main v-if="show[3]">

            </el-main>
          </el-container>
        </el-main>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
import Handler from "@/components/Handler.vue";

export default {
  components: {Handler},
  data(){
    return{
      userName:'woshinidie',
      registerDate:'2023/4/1',
      articalNum:0,
      activeDay:10,
      fansNum:156,
      isMyself:true,              //对比后端传来的uid与页面本地存储的Windows.local里的uid是否相同，如果相同将isMyself赋值为true
      watchNum:1946,
      show:[]
    }
  },
  methods:{
    //编辑资料，页面跳转
    a1(){

    },
    //私聊，页面跳转
    a2(){

    },
    //跳转设置界面
    settings(){

    },
    //关注函数，查看别人主页时进行关注
    attention(){

    },
    //登出系统，退回到主页界面
    exit(){

    },
    concern(){
      this.show[0]=true;
      this.show[1]=false;
      this.show[2]=false;
      this.show[3]=false;
    },
    collect(){
      this.show[0]=false;
      this.show[1]=true;
      this.show[2]=false;
      this.show[3]=false;
    },
    artical(){
      this.show[0]=false;
      this.show[1]=false;
      this.show[2]=true;
      this.show[3]=false;
    },
    recent(){
      this.show[0]=false;
      this.show[1]=false;
      this.show[2]=false;
      this.show[3]=true;
    }
  }


}
</script>

<style>
.asd1{
  background-image: url("@/assets/123456.jpeg");
}
.main{
  padding-top: 30px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 0px;
  margin: 0px;

}
.main2{
  padding-top: 10px;
  width: 800px;
  background-color: #efe8e8;
  justify-content: right;
}
.selfIntroduction{
  width: 1600px;
  margin: 10px;
  background-color: #efe8e8;
  height: 170px;
  padding-top: 10px;
  padding-left: 50px;
  border: 3px solid #d9d4d4;
}
.introduct{
  padding-left:15px;
  padding: 0px;
  justify-content: center;
  font-size: 25px;
  text-align: center;
}
.toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  right: 0px;
  padding-left: 730px;
  padding-top: 0px;
}

</style>