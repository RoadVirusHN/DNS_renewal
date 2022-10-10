import Login from './views/user/Login.vue'
import Join from './views/user/Join2.vue'
import FindPW from './views/user/FindPW.vue'
import Dev from './assets/dev/dev.vue'
import Components from './views/Components.vue'
import ChangePW from './views/password/ChangePW.vue'
import SendLink from './views/password/SendLink.vue'
import Main from './views/main/Main.vue'
import SearchMain from './views/search/SearchMain.vue'
import Creater from './views/creater/Creater.vue'
import Withdrawal from './views/user/Withdrawal.vue'
import Withdrawal2 from './views/user/Withdrawal2.vue'
import Withdrawal3 from './views/user/Withdrawal3.vue'
import UpdateUserinfo from './views/user/UpdateUserinfo.vue'
import SecuritySetting from './views/userpage/setting/Security/SecuritySetting.vue'
import BoardFeed from './views/userpage/BoardFeed.vue'
import CurationTest from './views/Curationtest.vue'
import Reply from './views/reply/Reply.vue'
import Store from './vuex/index'
import RegFeed from './views/feed/RegFeed.vue'
import UpdateFeed from './views/feed/UpdateFeed.vue'
import Userpage from './views/userpage/UserPageMain.vue'
import ReplyWrite from './views/reply/ReplyWrite.vue'
import Follower from './views/userpage/Follower.vue'
import Following from './views/userpage/Following.vue'
import NoticeSetting from './views/userpage/setting/Notice/NoticeSetting.vue'
import UserSettingMain from './views/userpage/setting/UserSettingMain.vue'
import CurationList from './views/userpage/setting/Curation/CurationList.vue'
import CreateCurationSet from './views/userpage/setting/Curation/CreateCurationSet.vue'
import PrivateUserSetting from './views/userpage/setting/PrivateSetting/PrivateUserSetting.vue'
import HashtagDetail from './components/Hashtags/HashtagDetail.vue'
import Notice from './views/userpage/notice/Notice.vue'
import FeedDetail from './views/feed/FeedDetail.vue'
import ScrapeFeed from './views/userpage/ScrapeFeed.vue'

const rejectAuthUser = (to, from, next) => {
    if (Store.getters.isAuthenticated === false) {
        alert("로그인 해야 사용하실 수 있습니다.")
        next("/")
    } else {
        next()
    }
}

export default [


    {
        path: '/',
        name: 'Login',
        component: Login,
    },
    {
        path: '/dev',
        name: 'Dev',
        component: Dev
    },
    {
        path: '/user/join',
        name: 'Join',
        component: Join
    },
    {
        path : '/user/findPW',
        name : 'FindPW',
        // beforeEnter: rejectAuthUser,
        component : FindPW
    },
    {
        path: '/password/ChangePW',
        name: 'ChangePW',
        component: ChangePW
    },
    {
        path: '/password/SendLink',
        name: 'SendLink',
        component: SendLink
    },
    {
        path: '/main',
        name: 'Main',
        component: Main,
        beforeEnter: rejectAuthUser
    },
    {
        path: '/components',
        name: 'Components',
        component: Components
    },
    {
        path: '/userpage/:userid',
        name: 'Userpage',
        component: Userpage,
        beforeEnter: rejectAuthUser,
        props: true
    },
    {
        path: '/search/SearchMain',
        name: 'SearchMain',
        component: SearchMain,
        beforeEnter: rejectAuthUser,
    },
    {
        path: '/creater/Creater',
        name: 'Creater',
        component: Creater
    },
    {
        path: '/user/withdrawal/:userindex',
        name: 'withdrawal',
        component: Withdrawal,
        beforeEnter: rejectAuthUser,
    },

    {
        path: '/user/withdrawal2/:userindex',
        name: 'withdrawal2',
        component: Withdrawal2,
        props:true,
        beforeEnter: rejectAuthUser,
    },
    {
        path: '/user/withdrawal3',
        name: 'withdrawal3',
        component: Withdrawal3
    },
    {
        path: '/user/UpdateUserinfo/:u_index',
        name: 'UpdateUserinfo',
        component: UpdateUserinfo,
        props:true,
        beforeEnter: rejectAuthUser
    },
    {
        path: '/userpage/BoardFeed/:b_index',
        name: 'BoardFeed',
        component: BoardFeed,
        props: true,
        beforeEnter: rejectAuthUser
    },
    {
        path: '/CurationTest',
        name: 'CurationTest',
        component: CurationTest,
        props: true
    },
    {
        path : '/reply/:bIndex',
        name : 'Reply',
        component : Reply,
        props:true,
        beforeEnter: rejectAuthUser
    },
    {
        path: '/user/:userid/follower',
        name : 'Follower',
        component : Follower,
        beforeEnter: rejectAuthUser

    },
    {
        path: '/user/following',
        name : 'Following',
        component : Following,
        beforeEnter: rejectAuthUser

    },
    {
        path: '/user/scrapeFeed',
        name : 'ScrapeFeed',
        component : ScrapeFeed,
        beforeEnter: rejectAuthUser

    },
    {
        path: '/user/securitysetting',
        name: 'SecuritySetting',
        component: SecuritySetting,
        beforeEnter: rejectAuthUser
    },
    {
        path: '/user/noticesetting',
        name : 'NoticeSetting',
        component : NoticeSetting,
        beforeEnter: rejectAuthUser
    },
    {
        path: '/user/usersetting',
        name : 'UserSettingMain',
        component :UserSettingMain,
        beforeEnter: rejectAuthUser
    },
    {
        path: '/replywrite/:boardindex',
        name: 'ReplyWrite',
        component: ReplyWrite,
        props: true,
        beforeEnter: rejectAuthUser
    },
    {
      path:'/regFeed',
      name : 'RegFeed',
      component: RegFeed,
      beforeEnter: rejectAuthUser
    }
    ,
    {
        path:'/updatefeed/:b_index',
        name : 'UpdateFeed',
        component: UpdateFeed,
        props:true,
        beforeEnter: rejectAuthUser
    }
    ,
    {
        path:'/Curationlist',
        name: 'CurationList',
        component: CurationList,
        beforeEnter: rejectAuthUser
    },
    {
        path:'/CreateCurationSet',
        name: 'CreateCurationSet',
        component: CreateCurationSet,
    },
    {
        path:'/user/PrivateUserSetting',
        name: 'PrivateUserSetting',
        component: PrivateUserSetting,
        beforeEnter: rejectAuthUser
    },
    {
        path:'/hashtag/:hashtagId',
        name: 'HashtagDetail',
        component: HashtagDetail,
        props: true,
        beforeEnter: rejectAuthUser
    },
    {
        path:'/user/Notice',
        name:'Notice',
        component: Notice,
        beforeEnter: rejectAuthUser
    },
    {
      path: '/FeedDetail/:b_index',
      name : 'FeedDetail',
      component:FeedDetail,
      props:true
    },

]
