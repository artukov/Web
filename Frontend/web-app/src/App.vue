<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>|
      <router-link to="/about">About</router-link>|
      <router-link to="/register">Register</router-link>|
      <router-link to="/login">Login</router-link>|
      <router-link to="/profile">Profile</router-link>|
      <router-link to="/searchUsers">Search users</router-link>|
    </div>
    <router-view />
  </div>
</template>



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
