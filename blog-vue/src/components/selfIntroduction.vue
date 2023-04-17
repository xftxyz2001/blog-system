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
              <el-icon style="padding-left: 20px"><Ship /></el-icon>
              <el-icon style="padding-left: 20px"><Female /></el-icon>
              <el-icon style="padding-left: 20px"><Sunrise /></el-icon>
              <span class="toolbar">
                                <el-button style="font-size: 20px;text-align: center;background-color: #efe8e8;border-color: black" v-if="isMyself" @click="settings">
                                    <el-icon><Tools /></el-icon>
                                </el-button>
                                <el-button style="font-size: 20px;text-align: center;background-color: #efe8e8;border-color: black" v-else  @click="concernButt">
                                    <el-icon  v-if="notConcern"><Plus /></el-icon>
                                    <el-icon v-else><Check /></el-icon>
                                </el-button>
                                <el-dropdown style="justify-content: center;padding-left: 35px">
                                   <el-icon style="font-size: 40px"><ChatDotRound /></el-icon>
                                    <template #dropdown>
                                        <!--这里要进行windows.local与本uid进行比对，若相同显示1菜单，不同说明访问的是别人的主页显示2菜单-->
                                      <!--1菜单-->
                                        <el-dropdown-menu v-if="isMyself">
                                            <el-button text @click="dialogData = true">编辑资料</el-button>
                                            <el-dropdown-item @click="exit">登出系统</el-dropdown-item>
                                            <el-dialog v-model="dialogData" title="修改个人资料">
                                                <el-form :model="form">
                                                    <el-form-item label="昵称" :label-width="formLabelWidth">
                                                        <el-input style="width: 60vh;"></el-input>
                                                    </el-form-item>
                                                    <el-form-item label="性别" :label-width="formLabelWidth">
                                                        <el-select  placeholder="请选择你的性别">
                                                            <el-option label="男"></el-option>
                                                            <el-option label="女"></el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                </el-form>
                                                <template #footer>
                                                  <span class="dialog-footer">
                                                    <el-button @click="dialogData = false">取消</el-button>
                                                    <el-button type="primary" @click="dialogData = false">确认</el-button>
                                                  </span>
                                                </template>
                                            </el-dialog>

                                        </el-dropdown-menu>
                                        <el-dropdown-menu v-else>
                                            <el-dropdown-item @click="clickChat" type="primary">私聊
                                            </el-dropdown-item>
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
        <el-main class="main2">
          <el-row>
            <el-col :span="4" style="background-color: transparent;margin-right: 20px;" :offset="1">
              <el-card class="box-card">
                <template #header>
                  <div class="card-header" style="font-size: 18px">
                    <span>个人成就</span>
                    <el-icon style="font-size: 30px"><View /></el-icon>
                  </div>
                </template>
                <el-row>
                  <div style="font-size: 20px;margin-bottom: 10px">
                    <el-icon style="padding-right: 20px"><Pointer /></el-icon>
                    总点击量为：{{totalPoint}} 次
                  </div>
                </el-row>
                <el-row>
                  <div style="font-size: 20px;margin-bottom: 10px">
                    <el-icon style="padding-right: 20px"><StarFilled /></el-icon>
                    总收藏量为：{{totalCollected}} 次
                  </div>
                </el-row>
                <el-row>
                  <div style="font-size: 20px;margin-bottom: 10px">
                    <el-icon style="padding-right: 20px"><Comment /></el-icon>
                    总评论量为：{{totalComment}} 条
                  </div>
                </el-row>
              </el-card>
              <el-card class="box-card2">
                <template #header>
                  <div class="card-header" style="font-size: 18px ">
                    <span>编辑博客</span>
                    <el-icon style="font-size: 30px"><Edit /></el-icon>
                  </div>
                </template>
                <el-row>
                  <div style="font-size: 20px">
                    去编辑一个自己的博客
                  </div>
                </el-row>
                <el-row style="display: inline-flex;
                                            align-items: center;
                                            justify-content: center;margin-top: 25px">
                  <el-button style="font-size: 40px;border-color: transparent">
                    <el-icon><EditPen /></el-icon>
                  </el-button>
                </el-row>
              </el-card>
            </el-col>
            <el-col :span="18" style="background-color: #e6e9ec;height:800px;padding: 0px;"><el-container>
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
                  <el-avatar :size= "100" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                  &nbsp&nbsp
                  <el-col span="650px" style="font-size: 30px;padding-top:15px;padding-left: 12px">
                    <el-row style="padding-top: 10px">{{ userName }}
                      <span style="font-size: 20px">&nbsp&nbsp加入本博客日期:&nbsp{{registerDate}}
                                            </span>
                      <el-icon style="padding-left: 20px"><Ship /></el-icon>
                    </el-row>
                    <el-row style="font-size: 20px;">
                                            <span style="padding-top: 15px">
                                                {{signal}}
                                            </span>
                      <el-button
                          style="font-size: 20px;
                                                    text-align: center;
                                                    background-color: #efe8e8;
                                                    border-color: black;
                                                    margin-left: 80px;
                                                    " @click="concernButt">
                        <el-icon  v-if="notConcern"><Plus /></el-icon>
                        <el-icon v-else><Check /></el-icon>
                      </el-button>
                    </el-row>
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
            </el-col>
          </el-row>

        </el-main>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
import Handler from "@/components/Handler.vue";
import {
  ChatDotRound,
  Check, Comment, Edit, EditPen,
  Female,
  Plus,
  Pointer,
  Ship,
  StarFilled,
  Sunrise,
  Tools,
  View
} from "@element-plus/icons-vue";

export default {
  components: {
    EditPen,
    Edit,
    Comment, StarFilled, Pointer, View, ChatDotRound, Check, Plus, Tools, Sunrise, Female, Ship, Handler},
  data(){
    return{
      dialogData: false,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formLabelWidth: '70px',
      userName:'woshinidie',
      registerDate:'2023/4/1',
      articalNum:0,
      activeDay:10,
      fansNum:156,
      isMyself:true,              //对比后端传来的uid与页面本地存储的Windows.local里的uid是否相同，如果相同将isMyself赋值为true
      watchNum:1946,
      show:[],
      notConcern:true,
      totalPoint:1380,
      totalCollected:510,
      totalComment:38,
      signal:'本科大三学生、22年度博客之星前端TOP4、华为云享专家、阿里云专家博主、51CTO博客TOP红人。前端之行'
    }
  },
  methods:{
    clickChat()
    {
      window.location.href='/#/chat';
    },
    //编辑资料，页面跳转,后端对应改数据库中内容
    a1() {

    },
    //跳转设置界面
    settings() {
      this.$confirm('是否允许他人访问自己的主页', '隐私设置', {
        confirmButtonText: '确定',
        cancelButtonText: '拒绝',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '您已设置为允许访问!'
        });
      }).catch(() => {
        this.$message({
          type: 'error',
          message: '您已拒绝他人访问'
        });
      });
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
    },
    concernButt(){
      if(this.notConcern === true)

      {
        this.notConcern=false;
        this.fansNum+=1;
      }
      else

      {
        this.notConcern=true;
        this.fansNum-=1;
      }
    }

  }


}
</script>

<style>
.box-card2{
  height: 250px;
  margin-top: 35px;
}
.box-card{
  height: 250px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 50px;
}
.asd1{
  background-image: url("@/assets/0000-0720.gif");
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
  padding-top: 20px;
  width: 1800px;
  background-color: transparent;
  text-align: center;
  overflow: hidden;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 0px;
  margin-left: 10px;
  padding-right: 50px;

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
