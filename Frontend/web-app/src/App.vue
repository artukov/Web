<template>
  <div id ="app">

    <!-- <nav-bar></nav-bar>
    <router-view/> -->



    <div id="nav">
      <b-navbar>
        <b-navbar-nav>
          <router-link to="/home">Home</router-link>|
          <router-link to="/about">About</router-link>|
          <router-link to="/register" v-if="this.$store.state.user == null">Register</router-link>|
          <router-link to="/" v-if="this.$store.state.user == null">Login</router-link>|
          <router-link to="/profile" v-if="this.$store.state.user != null">Profile</router-link>|
          <router-link to="/addApartment" v-if="this.$store.state.role == 'HOST'">Add apartment</router-link>|
          <router-link to="/myApartments" v-if="this.$store.state.role == 'HOST'">My apartments</router-link>|
          <!-- <router-link to="/" @click="logout" v-if="this.$store.state.user != null">Logout</router-link>| -->
          
          <b-nav-item-dropdown text="Sidebar" left>
          <router-link to="/searchUsers">Search users</router-link>
          
          
          </b-nav-item-dropdown>|
        </b-navbar-nav>
        <button id="dugme" @click="logout" v-if="this.$store.state.user != null"><b>Logout</b></button>
      </b-navbar>
      
    </div>
    <router-view />
  </div>
</template>

<script>
// import axios from "axios";
// import NavBar from "@/components/NavBar.vue";
export default {
  components: {
    // "nav-bar" : NavBar
  },
  data() {
    return {
      profileShow: false,
      idemo: ''
    }
  },

  mounted() {
      console.log("usao");

      if(this.$store.state.user == null) {
        console.log("nije logovan");
      } else {
        console.log("jeste logovan");
      }
      
      // if(this.$store.state.user.data.role == 'GUEST'){
      //   this.profileShow = true;
      // }
      // if(this.$store.state.user == null){
      //   console.log("rokaj")
      //   this.$store.state.user.pole.authority == 'GUEST';
      // }
      // axios
      // .get("/Web/rest/currentUser")
      // .then(response => {
      //   this.user = response.data;
      // })
      // .catch(error => {
      //   console.log(error);
      // })
    // axios
    //   .get("ads/ads")
    //   .then(ads => {
    //       console.log("usao u listu")
    //     this.ads = ads.data;
    //   })
    //   .catch(error => {
    //     console.log(error);
    //   });



  },
  methods: {
    logout() {
      this.$store.state.user = null;
      this.$store.state.role = "";
      window.location.reload(true);
      console.log("logout");
    }
  }
};
</script>


<style lang="scss">

 body {
    background-image: url('https://i.pinimg.com/originals/84/6c/b3/846cb34da29e27a5de7e76d0e0b613aa.jpg');
    background-repeat: no-repeat;

    /* background-color: #ECE0BD; */
    
} 


#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

#dugme {
  padding: 0px;
  margin: 0px;
  background-color: Transparent;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
}
</style>
