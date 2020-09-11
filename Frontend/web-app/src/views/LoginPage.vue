<template>
  <div class="position">
    <b-form @submit.prevent="onSubmit()" method="post">
      <b-form-group id="input-group-2" label="Username:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.username"
          type="text"
          required
          placeholder="Enter username"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-1" label="Password:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.password"
          type="password"
          required
          placeholder="Enter password"
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Log in</b-button>
    </b-form>
    <p></p>
    <router-link to="/register">Dont have an account? Register now</router-link>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      form: {
        password: "",
        username: "",
      },
      users: [],
      info: null,
      error: false,
      errorMessage: "",
    };
  },
  methods: {
    onSubmit() {
      console.log("ADS")
      // var user = {
      //   username: this.form.name,
      //   password: this.form.password,
      // };
      // axios.post("http://localhost:8080/Web/rest/login", user);
      axios.post("/Web/rest/login", this.form)
      .then(response => {
        this.form.password = "";
        this.form.username = "";
        response;
        if(response.status == 404) {
          console.log("nisce");
        } else if(response.status == 200){
          console.log("idemoo");
          console.log(response);
          this.$store.state.user = response;
          this.$store.state.role = this.$store.state.user.data.role;
          this.$router.push("/home");
        }
        // 
      })
      .catch(error => {
        this.errorMessage = "Bad credentials."
        this.error = true;
        error;
      })
    },
  },

  mounted() {
      axios.get("/Web/rest/users")
      .then(users => {
        this.users = users.data;
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>

<style scoped>
.position {
  width: 20%;
  height: 100%;
  margin-left: auto;
  margin-right: auto;
  margin-top: 30px;
}
</style>
