<template>
  <el-header>
    <div id="login">
      <Handler id="handler"></Handler>
    </div>
  </el-header>
    <el-container class="layout-container-demo" style="height: 982px ; width : 2114px">
        <el-aside width="200px">
            <el-scrollbar>
                <el-menu :default-openeds="['1', '2', '3']">
                    <el-sub-menu index="1">
                        <template #title>
                            <el-icon><message /></el-icon>发布博客管理
                        </template>
                        <el-menu-item-group>
                            <template #title class="group">Group 1</template>
                            <el-menu-item index="1-1" @click = "edit">编辑博客</el-menu-item>
                            <el-menu-item index="1-2" @click = "review">审核中博客</el-menu-item>
                            <el-menu-item index="1-3" @click = "published">已发布博客管理</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="2">
                        <template #title>
                            <el-icon></el-icon>个人信息管理
                        </template>
                        <el-menu-item-group>
                            <template #title class="group">Group 1</template>
                            <el-menu-item index="2-1" @click = "selfIntroduction">个人简介</el-menu-item>
                            <el-menu-item index="2-2" @click = "collect">收藏博客</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="3">
                        <template #title>
                            <el-icon><setting /></el-icon>个人好友管理
                        </template>
                        <el-menu-item-group>
                            <template #title class="group">Group 1</template>
                            <el-menu-item index="3-1" @click = "friendinfo">好友信息</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                </el-menu>
            </el-scrollbar>
        </el-aside>

        <el-container>
            <el-header style="text-align: right; font-size: 12px">
                <div class="toolbar">
                    <el-dropdown>
                        <el-button>张三</el-button>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item @click = "selfIntroduction">个人中心</el-dropdown-item>
                                <el-dropdown-item>博客主页</el-dropdown-item>
                                <el-dropdown-item>登出</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
            </el-header>
            <!-- 编辑博客 show0-->
            <el-main v-if="show[0]">

            </el-main>
            <!-- 审核中博客 show1-->
            <el-main v-if = "this.show[1]">
                <el-scrollbar>
                    <el-table :data="reviewBlog">
                        <el-table-column lable="" width="50"></el-table-column>
                        <el-table-column prop="blogName" label="Blog" width="300" />
                        <el-table-column prop="userName" label="Name" width="120" />
                        <el-table-column prop="date" label="Date" width="140" />
                        <el-table-column prop="blogLable" label="Lable" width="280"/>
                        <el-table-column label="State">
                                <el-button size="mini" type = "info">{{reviewB.state}}</el-button>
                        </el-table-column>
                    </el-table>
                </el-scrollbar>
            </el-main>
            <!-- 已发布博客管理 show2-->
            <el-main v-if="this.show[2]">
                <el-scrollbar>
                    <el-table :data="tableData">
                        <el-table-column lable="" width="50"></el-table-column>
                        <el-table-column prop="blogName" label="Blog" width="300" />
                        <el-table-column prop="userName" label="Name" width="120" />
                        <el-table-column prop="date" label="Date" width="140" />
                        <el-table-column prop="likeCount" label="Like" width="130" />
                        <el-table-column prop="collectionNum" label="Collect" width="130"/>
                        <el-table-column prop="blogLable" label="Lable" width="280"/>
                        <el-table-column label="State">
                            <el-button size="mini" type = "success">{{item.state}}</el-button>
                        </el-table-column>
                    </el-table>
                </el-scrollbar>
            </el-main>
            <!--个人简介 show4 -->
            <el-main v-if = "selfInc" style="text-align: -webkit-center" class = "background">
                <div>&nbsp</div>
                <div>&nbsp</div>
                <el-col :span="12">
                    <div class="sub-title"></div>
                    <div class="demo-basic--circle" >
                        <div class="block"><el-avatar :size="200" :src="circleUrl"></el-avatar></div>
                        <div class="block" v-for="size in sizeList" :key="size">
                        </div>
                    </div>
                </el-col>
                <br>
                <br>
                <div style="font-size: 25px" class="geren">个人昵称:&nbsp{{userName}}</div>
                <br>
                <div style="font-size: 25px" class="geren">个人签名:&nbsp{{userSign}}</div>
                <br>
                <div style="font-size: 25px" class="geren">注册为本博客用户日期:&nbsp{{registerDate}}</div>
                <br>
                <div style="font-size: 25px" class="geren">收藏博客数:&nbsp{{collectedNum}}</div>

            </el-main>
            <!-- 收藏博客 show5 -->
            <el-main v-if = "show[5]">
                <el-scrollbar>
                    <el-table :data="collectedData">
                        <el-table-column prop="blogName" label="Blog" width="300" />
                        <el-table-column prop="author" label="Author" width="120" />
                        <el-table-column prop="date" label="Date" width="140" />
                        <el-table-column prop="likeCount" label="Like" width="130" />
                        <el-table-column prop="collectionNum" label="Collect" width="130"/>
                        <el-table-column prop="blogLable" label="Lable" width="280"/>
                        <el-table-column label="State">
                            <el-button size="mini" type = "warning" @click = "destroyCollected">取消收藏</el-button>
                        </el-table-column>
                    </el-table>
                </el-scrollbar>
            </el-main>
            <!-- 好友信息 show6 -->
            <el-main v-if = "show[6]">
                <el-scrollbar>
                    <el-table :data="friendInfo">
                        <el-table-column lable="" width="80"></el-table-column>
                        <el-table-column prop="friendId" label="好友ID" width=""></el-table-column>
                        <el-table-column prop="friendName" label="好友昵称" width=""></el-table-column>
                        <el-table-column prop="beFriDate" label="加好友日期" width=""></el-table-column>
                        <el-table-column prop="sameLike" label="共同关注" width=""></el-table-column>
                        <el-table-column label="">
                            <el-button size="mini" type = "danger" @click = "destroyFriend">删除好友</el-button>
                        </el-table-column>
                    </el-table>
                </el-scrollbar>
            </el-main>
        </el-container>
    </el-container>
</template>

<script scope>
    import {ref} from "vue";
    import { Menu as IconMenu, Message, Setting} from '@element-plus/icons-vue'
    import { reactive, toRefs } from 'vue'
    import Handler from "../components/Handler.vue";
    export default {
        name:"selfIntroduction",
      components: {Handler, Setting, Message},
        data(){
            return{
                collectedNum:'56',
                registerDate:'2023/4/8',
                userName:'1245678',
                userSign:'我是神里绫华的gg',
                circleUrl: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
                sizeList: ["large", "medium", "small"],
                item:{              //后端传输已发布博客数据到这个位置，可以通过state属性进行区分
                    blogName: 'vue3与springboot结合开发博客系统',
                    userName: '张三',
                    date: '2023-03-11',
                    likeCount: '136',
                    collectionNum: '245',
                    blogLable: 'vue3\t\tspringboot\t\t前端开发\t\t后端开发',
                    state:'已通过'
                },
                reviewB:{           //后端传审核中博客数据到这个位置
                    blogName: 'vue3与springboot结合开发博客系统',
                    userName: '张三',
                    date: '2023-03-11',
                    blogLable: 'vue3\t\tspringboot\t\t前端开发\t\t后端开发',
                    state: '审核中......'
                },
                collected:{         //后端传输收藏博客信息数组
                    blogName: 'vue3与springboot结合开发博客系统',
                    date: '2023-03-11',
                    likeCount: '136',
                    collectionNum: '245',
                    blogLable: 'vue3\t\tspringboot\t\t前端开发\t\t后端开发',
                    author:'李四'
                },
                friends:{           //后端传输好友信息数组，需要的属性如下
                    friendId: '1846562132',
                    friendName: 'Joker',
                    beFriDate: '2023/4/8',
                    sameLike: '53',
                },
                tableData :[],
                show:[],
                reviewBlog:[],
                collectedData:[],
                friendInfo:[],
                selfInc:true
            }
        },
        methods:{
            edit(){
                this.show[0] = true;
                this.show[1] = false;
                this.show[2] = false;
                this.show[3] = false;
                this.selfInc = false;
                this.show[5] = false;
                this.show[6] = false;
            },
            review(){
                this.show[0] = false;
                this.show[1] = true;
                this.show[2] = false;
                this.show[3] = false;
                this.selfInc = false;
                this.show[5] = false;
                this.show[6] = false;
                this.reviewBlog = ref(Array.from({length:10}).fill(this.reviewB));
            },
            published(){
                this.show[0] = false;
                this.show[1] = false;
                this.show[2] = true;
                this.show[3] = false;
                this.selfInc = false;
                this.show[5] = false;
                this.show[6] = false;
                this.tableData = ref(Array.from({ length: 10 }).fill(this.item));
            },
            selfIntroduction(){
                this.show[0] = false;
                this.show[1] = false;
                this.show[2] = false;
                this.show[3] = false;
                this.selfInc = true;
                this.show[5] = false;
                this.show[6] = false;
            },
            collect(){
                this.show[0] = false;
                this.show[1] = false;
                this.show[2] = false;
                this.show[3] = false;
                this.selfInc = false;
                this.show[5] = true;
                this.show[6] = false;
                this.collectedData = ref(Array.from({ length: 10 }).fill(this.collected));
            },
            friendinfo(){
                this.show[0] = false;
                this.show[1] = false;
                this.show[2] = false;
                this.show[3] = false;
                this.selfInc = false;
                this.show[5] = false;
                this.show[6] = true;
                this.friendInfo = ref(Array.from({ length: 10 }).fill(this.friends));
            },
            destroyCollected(){         //点击取消收藏按钮后端数据库收藏表中删去对应行

            },
            destroyFriend(){            //点击删除好友按钮后从数据库中删除好友

            }
        }
    }
</script>


<style scoped>
    .layout-container-demo .el-header {
        position: relative;
        color: var(--el-text-color-primary);
    }
    .layout-container-demo .el-aside {
        color: var(--el-text-color-primary);
    }
    .layout-container-demo .el-menu {
        border-right: none;
    }
    .layout-container-demo .el-main {
        padding: 0;
    }
    .layout-container-demo .toolbar {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        height: 100%;
        right: 0px;
    }
    .layout-container-demo .image-slot {
        font-size: 30px;
    }
    .layout-container-demo .image-slot .el-icon {
        font-size: 30px;
    }
    .layout-container-demo .el-image {
        width: 100%;
        height: 200px;
    }
/*    .el-menu-item{
      background-color: #696c70;
    }
    .el-menu-item-group{
      background-color: #696c70;
    }
    .el-scrollbar{
      background-color: #696c70;
    }*/
    .background{
        background-image: url("@/assets/dd.jpeg");
    }
    html,body,#app,.el-container{
      padding: 0px;
      margin: 0px;
      height: 100%;
      border: 0;
    }
/*    .geren{
      color: white;
    }
    .group{
      background-color: #696c70;
    }*/
</style>