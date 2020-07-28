<template>
  <div class="position">
    <b-form @submit.prevent="onSubmit()" method="post">
      <b-form-group id="input-group-2" label="Username:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
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
        name: "",
      },
      info: null,
    };
  },
  methods: {
    onSubmit() {
      var user = {
        username: this.form.name,
        password: this.form.password,
      };
      axios.post("http://localhost:8080/Backend/Web/rest/login", user);
    },
  },
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
