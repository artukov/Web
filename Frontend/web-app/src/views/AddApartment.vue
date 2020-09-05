<template>

<div class="position">
    <b-form @submit.prevent="onSubmit" method="post">
      <b-form-group id="input-group-2" label="Apartment types:" label-for="input-2">
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
export default {
    data() {
        return {
            form: {
                apartmentType: "",
                numberRooms: "",
                guestNumber: "",
                location: "",
                apartmentDates: "",
                host: "",
                comments: "",
                priceNight: "",
                checkIn: "",
                checkOut: "",
                appStatus: "",
                amenities: "",
                reservations: ""
            },
            types: ["ROOM", "COMPLETE"],
        }
    }
}
</script>
