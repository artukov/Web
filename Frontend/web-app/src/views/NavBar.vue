<template>
  <div id="app">


    <div id="nav">
      <b-navbar>
        <b-navbar-nav>
          <router-link to="/home">Home</router-link>|
          <router-link to="/about">About</router-link>|
          <router-link to="/register">Register</router-link>|
          <router-link to="/">Login</router-link>|
          <router-link to="/profile" v-if="this.$store.state.user != null">Profile</router-link>|
          <router-link to="/">Logout</router-link>|
          <b-nav-item-dropdown text="Sidebar" left>
          <router-link to="/searchUsers">Search users</router-link>
          </b-nav-item-dropdown>|
        </b-navbar-nav>
      </b-navbar>
    </div>
    <div><button></button></div>
  </div>
  
</template>

<script>
// import axios from "axios";
export default {
  data() {
    return {
      profileShow: false,
      user: ''
    }
  },

  mounted() {
      console.log("usao");
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
</style>
