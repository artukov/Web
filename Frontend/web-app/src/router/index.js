import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import RegisterPage from "../views/RegisterPage.vue";
import LoginPage from "../views/LoginPage.vue";
import ProfilePage from "../views/ProfilePage.vue";
import SearchUsers from "../views/SearchUsers.vue";
import AddApartment from "../views/AddApartment.vue";
import MyApartments from "../views/MyApartments.vue";
import Apartments from "../views/Apartments.vue"
import Amenities from "../views/Amenities.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {
    path: "/",
    name: "LoginPage",
    component: LoginPage,
  },
  {
    path: "/register",
    name: "RegisterPage",
    component: RegisterPage,
  },
  {
    path: "/profile",
    name: "ProfilePage",
    component: ProfilePage,
  },
  {
    path: "/searchUsers",
    name: "SearchUsers",
    component: SearchUsers,
  },
  {
    path: '/addApartment',
    name: 'add-apartment-ad',
    component: AddApartment
  },
  {
    path: '/myApartments',
    name: 'MyApartments',
    component: MyApartments
  },
  {
    path: '/apartments',
    name: 'Apartments',
    component: Apartments
  },
  {
    path: '/amenities',
    name: 'Amenities',
    component: Amenities
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
