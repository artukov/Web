<template>
  <div class="position">







<b-container>
         <div>
    <b-table class="mt-2 mb-2" striped hover :items="users"  selectable caption-top bgcolor="white">
          <!-- <template v-slot:table-caption><h3>Ads list</h3>
          </template> -->
    </b-table>
  </div>
</b-container>












<!-- @submit.prevent="onSubmit" -->
    <b-form  @submit.prevent="onSubmit"  method="get">
      <b-form-group id="input-group-2" label="Username:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.username"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Lastname:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.lastName"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Gender:" label-for="input-3">
        <b-form-select
          id="input-3"
          v-model="form.gender"
          :options="genders"
        ></b-form-select>
      </b-form-group>

      <b-form-group id="input-group-3" label="Role:" label-for="input-3">
        <b-form-select
          id="input-3"
          v-model="form.role"
          :options="roles"
        ></b-form-select>
      </b-form-group>

    <div>
      <b-button type="submit" variant="primary" class="btn-block z-depth-2">Search users</b-button>
    </div>
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
        name: "",
        lastName: "",
        gender: null,
        role: null,
      },
      genders: ["MALE", "FEMALE"],
      roles: ["GUEST", "HOST","ADMIN"],
      error: false,
      errorMessage: "",
      confirmPass: "",
      info: "",
      users: [],
      proba: "",
      povratak: ""
    };
  },
  methods: {

    onSubmit() {
      axios.post("/Web/rest/search", this.form)
        .then(users => {
          this.users = users.data;
          this.error = false;
          this.info = "Search successful.";
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
