<template>
  <el-row :gutter="100">
    <el-col :span="6"><div class="grid-content ep-bg-purple">
      <el-card class="box-card1">
        <template #header>
          <div class="card-header">
            <el-row class="demo-basic" :gutter="180">

              <el-col>
                <div >
                  <div class="block">
                    <el-avatar :size="50" :src="blogMD.picture"></el-avatar>
                  </div>
                </div>
              </el-col>
              <el-col><div>{{blogMD.username}}</div></el-col>
            </el-row>
          </div>
        </template>


        <div class ="message">
          <el-row>

            <el-col :span ="8">
              <el-row>{{blogMD.fans}}</el-row>
              <el-row>粉丝</el-row>
            </el-col>
            <el-col :span ="8">
              <el-row>{{blogMD.fans}}</el-row>
              <el-row>文章</el-row>
            </el-col>
            <el-col :span ="8">
              <el-row>{{blogMD.fans}}</el-row>
              <el-row>获赞</el-row>
            </el-col>
          </el-row>
        </div>

        <br>
        <div class ="buttons">
          <el-row>
            <el-col :span ="12"><el-button class="button" text>
              <el-button @click="clickchat" type="primary" >私聊</el-button>
            </el-button></el-col>
            <el-col :span ="12"><el-button class="button" text>
              <el-button @click="clickguanzhu" type="primary" style="background-color: white">
                <div class="guanzhu">
                  <img v-if="gz === false" :src="alarmImg5" @click="insertAttr3"  alt=""/>
                  <img v-if="gz === true" :src="alarmImg6" @click="insertAttr3"  alt=""/>
                </div>
              </el-button>
            </el-button></el-col>
          </el-row>
        </div>



      </el-card>
    </div>
    </el-col>
    <el-col :span="16"><div class="grid-content ep-bg-purple-light">
      <el-card class="box-card2">
        <template #header>
          <div class="card-header">

            <h2>{{ blogMD.title }}</h2>
            <p>{{$route.params.name}}</p>
          </div>
        </template>

        <div>{{blogMD.content}}</div>
      </el-card>
    </div></el-col>
  </el-row>
  <el-row :gutter="100">
    <el-col :span="16" :offset="6" >
      <el-card class="box-card3">
        <template #header>
          <div class="card-header">
                <span>{{ blogMD.username }}<el-button @click="clickguanzhu" class="button" text style="background-color: white">
                    <div class="guanzhu">
                                        <img v-if="gz === false" :src="alarmImg5" @click="insertAttr3"  alt=""/>
                                        <img v-if="gz === true" :src="alarmImg6" @click="insertAttr3"  alt=""/>
                                    </div>
                </el-button></span>
            <div class="dianzan">
              <img v-if="manyi === false" :src="alarmImg1" @click="insertAttr1"  alt=""/>
              <img v-if="manyi === true" :src="alarmImg2" @click="insertAttr1"  alt=""/>
              {{this.support1}}
            </div>
            <div class="shoucang">
              <img v-if="sc === false" :src="alarmImg3" @click="insertAttr2"  alt=""/>
              <img v-if="sc === true" :src="alarmImg4" @click="insertAttr2"  alt=""/>
              {{this.support2}}
            </div>
            <el-button class="button3" text><el-icon><svg t="1681193927023" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1054" data-spm-anchor-id="a313x.7781069.0.i0" width="200" height="200"><path d="M704.6656 443.392H346.4704c-16.9472 0-30.72-13.7728-30.72-30.72s13.7728-30.72 30.72-30.72h358.1952c16.9472 0 30.72 13.7728 30.72 30.72s-13.7216 30.72-30.72 30.72zM597.504 578.4576H346.4704c-16.9472 0-30.72-13.7728-30.72-30.72s13.7728-30.72 30.72-30.72h250.9824c16.9472 0 30.72 13.7728 30.72 30.72s-13.7216 30.72-30.6688 30.72z" fill="#45CFFF" p-id="1055" data-spm-anchor-id="a313x.7781069.0.i3" class="selected"></path><path d="M414.7712 879.8208c-11.1616 0-22.3744-2.7136-32.6144-8.1408a69.3248 69.3248 0 0 1-36.864-61.3376v-43.1104c-0.0512-5.4784-4.864-10.24-10.6496-10.24h-56.32c-73.4208 0-133.12-59.6992-133.12-133.12V330.5984c0-73.4208 59.6992-133.12 133.12-133.12h501.9648c73.4208 0 133.12 59.6992 133.12 133.12v293.2736c0 73.4208-59.6992 133.12-133.12 133.12h-159.232c-2.3552 0-4.6592 0.768-6.5024 2.2016l-1.536 1.1264-159.2832 107.5712c-11.776 7.936-25.344 11.9296-38.9632 11.9296zM278.272 258.9184c-39.5264 0-71.68 32.1536-71.68 71.68v293.2736c0 39.5264 32.1536 71.68 71.68 71.68h56.32c39.3216 0 71.68 32 72.0896 71.3216v43.4176c0 3.9936 2.304 6.0928 4.2496 7.1168 1.9456 1.024 4.9664 1.792 8.2944-0.4096l158.6176-107.1104c12.3904-9.216 27.648-14.2848 43.1104-14.2848h159.232c39.5264 0 71.68-32.1536 71.68-71.68V330.5984c0-39.5264-32.1536-71.68-71.68-71.68H278.272z m317.4912 475.9552z" fill="#474747" p-id="1056"></path></svg></el-icon>评论</el-button>
          </div>
        </template>

        <div>
          <el-row v-for="item in newcomment_array" class="text item">
            <el-col :span="12"><el-avatar :size="50" :src="blogMD.picture"></el-avatar>{{item}}</el-col>
            <el-col v-for="items in subcomment_array">{{items}}</el-col>
            <el-col :span="3" :offset="21" style="float:right;text-align: right">
              <el-popover  placement="top" :width="320" trigger="hover">
                <el-input v-model="subcomment"
                          :autosize="{ minRows: 3, maxRows: 5 }"
                          type="textarea"
                          placeholder="请输入评论内容"></el-input>
                <div style="text-align: right; margin: 0">
                  <el-button size="small" text @click="subcomment=null ">clear</el-button>
                  <el-button size="small" type="primary"  @click="recomment(o)">OK</el-button>
                </div>
                <template #reference>
                  <el-button >回复</el-button>
                </template>
              </el-popover>
            </el-col>
          </el-row>


        </div>


        <el-input v-model="newcomment"
                  :autosize="{ minRows: 3, maxRows: 5 }"
                  type="textarea"
                  placeholder="请输入评论内容"></el-input>
        <el-button @click="addcomment">发布评论</el-button>
      </el-card>
    </el-col>
  </el-row>


</template>

<script>
export default{
  data(){
    return{
      alarmImg1: require("@/assets/点赞.png"),
      alarmImg2: require("@/assets/点赞亮.png"),
      alarmImg3: require("@/assets/收藏.png"),
      alarmImg4: require("@/assets/收藏亮.png"),
      alarmImg5: require("@/assets/加号.png"),
      alarmImg6: require("@/assets/对号.png"),
      manyi: false,
      sc:false,
      gz:false,
      newcomment:'',
      newcomment_array:[],
      subcomment:'',
      subcomment_array:[],
      support1:0,
      support2:0,
      support3:0,
      blogMD:{
        id:'博客id:111',//博客id
        userId:'用户id:222',//用户id
        username:'我妻由乃',
        picture:require("@/assets/2255.jpg"),
        title:'标题：关于什么什么的什么',//标题
        description: "简介：本文章讲了什么",//简介
        content: "当人人具备编写代码的能力之后，这将会给软件生产和分配带来哪些结构性的变化？\n" +
            "\n" +
            "原文链接：https://www.geoffreylitt.com/2023/03/25/llm-end-user-programming.html\n" +
            "\n" +
            "未经授权，禁止转载！\n" +
            "\n" +
            "作者 | Geoffrey Litt  译者 | 弯月\n" +
            "\n" +
            "责编 | 王子彧 \n" +
            "\n" +
            "出品 | CSDN（ID：CSDNnews）\n" +
            "\n" +
            "近段时间，大语言模型掀起了一股狂潮。 OpenAI 发布的 GPT-4 模型在包括编程在内的各个功能上都取得了令人瞩目的进步。微软研究院发布了一篇论文，展示了 GPT-4 能够在没有太多提示的情况下生成非常复杂的代码，如 3D 视频游戏。与此同时，OpenAI 还发布了 ChatGPT 插件。\n" +
            "\n" +
            "\n" +
            "\n" +
            "大语言模型将如何影响软件的构建？\n" +
            "\n" +
            "大语言模型能够提高专业开发人员的工作效率，GitHub Copilot 已经证明了这个方向的可行性。这意味着开发人员不必担心未来的工作前景，且软件的生产与分发方式不会发生剧烈的变化。\n" +
            "\n" +
            "然而，大语言模型的影响力不仅于此。大语言模型将成为专业程序员的工具，但过于关注狭隘的用途可能会导致我们错失未来推动更大变化的潜力。\n" +
            "\n" +
            "因为我认为在短时间内有可能所有的计算机用户都将有能力开发小型软件工具，并描述他们希望如何修改正在使用的软件。换句话说，大语言模型将发展成为支持用户编程的工具，普通人无需掌握编程的复杂性即可充分利用计算机。就目前的情况而言，这一愿景在将模糊的非正式意图转化为正式的可执行代码方面遇到了瓶颈。而接下来，在大规模语言模型的助力下，瓶颈正在迅速打开。\n" +
            "\n" +
            "如果这种假设成真，我们将看到人们使用软件的方式出现一些惊人的变化：\n" +
            "\n" +
            "一次性脚本：普通计算机用户可以借助 AI，每天创建并执行几十次脚本，以执行数据分析、视频编辑等任务，或自动执行一些繁琐的任务。\n" +
            "\n" +
            "一次性 GUI：人们将使用 AI 创建整个 GUI 应用程序，这种应用程序只能执行特定的某个任务，仅包含他们需要的功能，不会膨胀。\n" +
            "\n" +
            "构建取代购买：企业内部将开发更多软件，以满足他们特定的需求，而不是购买现成的 SaaS，因为根据具体情况定制软件更便宜。\n" +
            "\n" +
            "修改/扩展：消费者和企业需要扩展和修改现有软件的能力，在 AI 的助力下，开发新功能或调整匹配用户的工作流程将更容易。\n" +
            "\n" +
            "重组：吸取各个应用程序的精华，并创建一个集百家之长的混合应用程序。\n" +
            "\n" +
            "所有这些变化不仅仅是加快当前软件的生产过程。软件的创建时机、由谁创建以及出于什么目的，都将发生变化。\n" +
            "\n" +
            "\n" +
            "\n" +
            "大规模语言模型+可延展软件\n" +
            "接下来，将深入探讨大语言模型可能为软件创建和分发带来的广泛变化，而且还会影响到人们与软件交互的方式。讨论的问题包括：\n" +
            "\n" +
            "交互模型：哪种交互模型适合哪些任务？人们什么时候需要聊天机器人、一次性脚本或自定义的一次性 GUI？\n" +
            "\n" +
            "软件定制：大规模语言模型如何实现可由用户拆分、重组和扩展的可延展软件？\n" +
            "\n" +
            "意图规范：最终用户将如何与大规模语言模型交互，表达自己的意图？\n" +
            "\n" +
            "模糊翻译器：大规模语言模型如何实现以前不可能实现的共享数据基础？\n" +
            "\n" +
            "用户授权：我们应该如何看待大规模语言模型时代的授权和代理与授权和自动化？\n"
        ,//内容
        status: 0,//发布状态
        fans:144,
      },
    }
  },
  // created() {
  //     axios({
  //         method:'get',
  //         url:'/getSpeBlog',
  //         params: {
  //             bid:this.blogMD.bid
  //         }
  //     }).then(res=>{
  //         if(res.data.flag){
  //             this.$message.success(res.data.msg);
  //             this.MBlog = res.data.data;
  //         }
  //     })
  //     axios({
  //         method:'get',
  //         url:'/myHome/getUserBlog',
  //         params: {
  //             uid:this.MBlog.uid
  //         }
  //     }).then(res=>{
  //         if(res.data.flag){
  //             this.$message.success(res.data.msg);
  //             this.articleNum = res.data.data.length;
  //         }
  //     })
  //     console.log(this.MBlog.uid)
  //     axios({
  //         method:'get',
  //         url:'/myHome/getFans',
  //         params: {
  //             uid:this.MBlog.uid
  //         }
  //     }).then(res=>{
  //         if(res.data.flag){
  //             this.$message.success(res.data.msg);
  //             this.fans = res.data.data.length;
  //         }
  //     })
  //     axios({
  //         method:'get',
  //         url:'/getAllGoods',
  //         params:{
  //             uid:this.MBlog.uid
  //         }
  //     }).then(res=>{
  //         if(res.data.flag){
  //             this.allGoods = res.data.data;
  //         }
  //     })
  //
  //
  // },
  methods: {
    recomment(id){
      console.log(id);
      this.subcomment_array.push(this.subcomment);
      this.subcomment = '';
    },
    clickchat()
    {
      window.location.href='/#/chat';
    },
    insertAttr1() {
      if(this.manyi===false)
        this.support1=this.support1+1
      else
        this.support1=this.support1-1
      this.manyi = !this.manyi;
      // if (this.manyi === true) {
      //     addFeedback({
      //         title: this.text,
      //         status: "1",
      //         note: "",
      //         type: "1",
      //         userName: this.loginName,
      //     }).then((res) => {
      //     });
      // }
    },
    insertAttr2() {
      if(this.sc===false)
        this.support2=this.support2+1
      else
        this.support2=this.support2-1
      this.sc = !this.sc;
      // if (this.sc === true) {
      //     addFeedback({
      //         title: this.text,
      //         status: "1",
      //         note: "",
      //         type: "1",
      //         userName: this.loginName,
      //     }).then((res) => {
      //     });
      // }
    },
    insertAttr3() {
      if(this.gz===false){
        this.support3=this.support3+1;
        this.blogMD.fans=this.support3 + this.blogMD.fans;
      }

      else{
        this.support3=this.support3-1;
        this.blogMD.fans=this.blogMD.fans-1;
      }



      this.gz = !this.gz;
      // if (this.gz === true) {
      //     addFeedback({
      //         title: this.text,
      //         status: "1",
      //         note: "",
      //         type: "1",
      //         userName: this.loginName,
      //     }).then((res) => {
      //     });
      // }
    },
    addcomment: function(){

      this.newcomment_array.push(this.newcomment);
      this.newcomment = '';
    }
  },

}
</script>
<style scoped>
html{
  overflow-x: hidden;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.message
{

}


.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card1 {
  width: 100%;
  height:400px;
}
.box-card3 {
  width: 100%;
  height:100%;
}

</style>

<style lang="scss" scoped>
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
<style scoped>
.demo-basic {
  text-align: center;

}
.demo-basic .block:not(:last-child) {
  border-right: 1px solid var(--el-border-color);
}
.demo-basic .block {
  flex: 1;
}
.demo-basic .el-col:not(:last-child) {
  border-right: 1px solid var(--el-border-color);
}
</style>
