<template>
  <div class="position">
    <b-form @submit.prevent="onSubmit" method="post">
      <b-form-group id="input-group-2" label="Username:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.username"
          required
          readonly
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-1" label="Password:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.password"
          type="password"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-1" label="Confirm password:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="confirmPass"
          type="password"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Lastname:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.lastname"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Gender:" label-for="input-3">
        <b-form-select
          id="input-3"
          v-model="form.gender"
          :options="genders"
          required
        ></b-form-select>
      </b-form-group>

      <b-form-group id="input-group-3" label="Role:" label-for="input-3">
        <b-form-select
          id="input-3"
          v-model="form.role"
          :options="roles"
          required
        ></b-form-select>
      </b-form-group>

      <b-button type="submit" variant="primary">Change profile</b-button>
    </b-form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
        name: "",
        lastname: "",
        gender: "",
        role: "",
      },
      genders: ["Male", "Female"],
      roles: ["GUEST", "HOST"],
      error: false,
      errorMessage: "",
      confirmPass: "",
      info: ""
    };
  },
  methods: {
    mounted() {
    //   var user = {
    //     username: this.form.name,
    //     password: this.form.password,
    //     gender: this.form.gender,
    //     userRole: this.form.role,
    //     name: this.form.name,
    //     lastname: this.form.lastname,
    //   };
      axios.get("/Web/rest/currentUser", this.form)
      .then(form => {
        this.form = form.data;
        this.error = false;
      })
      .catch(error => {
        this.errorMessage = "Bad credentials."
        this.error = true;
        error;
      })
    },

    onSubmit() {
      if(this.form.password != this.confirmPass) {
        this.error = true;
        this.errorMessage = "Password does not match!";
        return;
      }
      axios.post("/Web/rest/changeUser", this.form)
        .then(form => {
          this.form = form.data;
          this.error = false;
          this.info = "User profile successfully changed.";
        })
        .catch(error => {
          this.errorMessage = "Bad credentials."
          this.error = true;
          error;
        })
    }
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
