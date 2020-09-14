<template>
  <div class="position">

    <b-container v-if="error">
      <b-alert show variant="danger" class="d-flex justify-content-center">{{errormessage}}</b-alert>
    </b-container>

     <b-container v-if="success">
      <b-alert show variant="success" class="d-flex justify-content-center">{{successmessages}}</b-alert>
    </b-container>


    <b-form @submit.prevent="onSubmit" method="post">
      <b-form-group id="input-group-2" label="Username:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.username"
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

      <b-form-group id="input-group-1" label="Confirm password:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="confirmPass"
          type="password"
          required
          placeholder="Enter password"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
          required
          placeholder="Enter name"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Lastname:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.lastName"
          required
          placeholder="Enter lastname"
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

      <b-button type="submit" variant="primary">Register</b-button>
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
        lastName: "",
        gender: null,
        role: null,
      },
      genders: ["MALE", "FEMALE"],
      roles: ["GUEST", "HOST"],
      confirmPass: "",
      error: false,
      errormessage: "",
      success: false,
      successmessages: ""
      
    };
  },
  methods: {
    onSubmit() {
    //   var user = {
    //     username: this.form.name,
    //     password: this.form.password,
    //     gender: this.form.gender,
    //     userRole: this.form.role,
    //     name: this.form.name,
    //     lastname: this.form.lastname,
    //   };
      if(this.form.password != this.confirmPass) {
        this.errormessage = "Passwords do not match!";
          this.error = true;
        return;
      }
      axios.post("/Web/rest/register", this.form)
      .then(response => {
        this.form = response.data;
        this.error = false;
        this.$store.state.user = response;
        this.$store.state.role = this.$store.state.user.data.role;
        this.success = true;
        this.successmessages = "User successfully registered.";
        this.error = false;
        this.$router.push("/home");
      })
      .catch(error => {
          console.log(error);
          this.errormessage = "Error occurred while registering.";
          this.error = true;
        });
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
