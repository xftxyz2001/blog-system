
    <template>
        <el-header style="background-color: rgba(107,195,13,0)">
            <div id="login">
                <Handler id="handler"></Handler>
            </div>
        </el-header>
        <div class="common-layout">
            <el-container>
                <el-aside width="200px">
                    <el-menu background-color="#545c64" text-color="#ffffff"
                             active-text-color="#ffd04b" default-active="2">
                        <el-menu-item index="1"><i class="el-icon-location"></i>{{ user.username }}</el-menu-item>
                       </el-menu>
                </el-aside>
                <el-container>
                    <el-main>
                        <el-card class="box-card">
                        <div>聊天内容</div>

                            <div id="app" v-for="item in message_array">

                                <el-row style="margin-left: 780px;white-space:normal;word-break:break-all;overflow:hidden;">
                                    <span>{{user.username}}</span>
                                    <el-avatar :size="50" :src="user.picture"></el-avatar>
                                </el-row>

                                <div style="margin-left: 700px;white-space:normal;word-break:break-all;overflow:hidden;">
                                <el-row>
                                    <el-col span="12" offset="12">
                                        {{item}}
                                        <br>
                                        <br>
                                    </el-col>
                                </el-row>
                                </div>
                            </div>

                    </el-card>
                    </el-main>
                    <el-footer>
                        <el-input
                                :rows="5"
                                type="textarea"
                                placeholder="请输入内容"
                                v-model="message"
                        >
                        </el-input>
                        <el-button
                                class="submit-btn"
                                type="primary"
                                @click="dealMessage"
                                :disabled="message === ''"
                        >发送
                        </el-button>
                    </el-footer>
                </el-container>
            </el-container>
        </div>
    </template>



<script>
    import Handler from "@/components/Handler.vue";

    export default {
        components: {Handler},
        data() {

            return {
                message_array:[],
                message:'',
                user: {
                    id: '博客id:111',//博客id
                    userId: '用户id:222',//用户id
                    username: '我妻由乃',
                    picture:require("@/assets/2255.jpg"),
                }
            }
        },
        methods:{
            dealMessage: function () {
                this.message_array.push(this.message);
                this.message = '';
            }
        }
    }
</script>
<style scoped>
    .common-layout{
        background-color:#99a9bf;
        height: 100vh;
    }
    .el-container
    {
        background-color:#99a9bf;

    }
    .el-main
    {
       height: 500px;
        width: 1000px;

    }
    .el-footer
    {
        height: 200px;
        width: 1000px;

    }
    .el-aside
    {
        margin-top: 20px;
        margin-left: 25vh;
        background-color: #444647;
        height: 88vh;

    }
    .submit-btn {
        margin: 0 15px 10px 0;
        float: right;
    }
    .text {
        font-size: 14px;
    }

    .item {
        padding: 18px 0;
    }

    .box-card {
        height: 600px;
        width: 960px;
        overflow-y: v-bind(450);
    }
</style>
