<template>

<div class="position">
    <b-form @submit.prevent="onSubmit" method="post">

      <b-form-group id="input-group-3" label="Apartment type:" label-for="input-3">
        <b-form-select
          id="input-3"
          v-model="form.apartmentType"
          :options="types"
          required
        ></b-form-select>
      </b-form-group>

      <b-form-group id="input-group-2" label="Number of rooms:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.numberRooms"
          required
          placeholder="Enter number of rooms"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Number of guests:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.guestNumber"
          required
          placeholder="Enter number of guests"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-1" label="Location:" label-for="input-1">
        <b-form-select v-model="selectedLocation">
          <option
            v-for="location in locations"
            :value="location.longitude"
            :key="location.longitude">
            {{location.longitude}}, {{location.latitude}}, {{location.address.city}}
          </option>
        </b-form-select>
      </b-form-group>

      <b-form-group id="input-group-3" label="Host:" label-for="input-2">
        <b-form-input
          id="input-2"
          
          required
          placeholder="Enter host"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Price per night:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.priceNight"
          required
          placeholder="Enter price"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Price per night:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.priceNight"
          required
          placeholder="Enter price"
        ></b-form-input>
      </b-form-group>

        <b-form-group id="input-group-3" label="Check in:" label-for="input-3">
          <b-form-input
            id="input-3"
            type="time"
            v-model="form.checkIn"
            required
          ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Check out:" label-for="input-2">
        <b-form-input
          id="input-2"
          type="time"
          v-model="form.checkOut"
          required
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Register</b-button>
      <b-button @click="idemo" variant="primary">Idemo</b-button>
    </b-form>
  </div>
    
</template>

<script>
import axios from "axios";
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
                checkIn: "14:00",
                checkOut: "10:00",
                appStatus: "",
                amenities: "",
                reservations: ""
            },
            types: ["ROOM", "COMPLETE"],
            selectedLocation: "",
            locations: [],
            amenities: []
        }
    },

    methods: {
      idemo() {
        axios.get("/Web/rest/amenities/all")
        .then(response => {
          this.locations = response.data;
          if(response.status == 200) {
            console.log("ide gaaaaas")
          }
        })
        .catch(error => {
          console.log(error);
        });
      }
    }

    ,

    mounted() {
      axios.get("/Web/rest/locations")
        .then(response => {
          this.locations = response.data;
          if(response.status == 200) {
            console.log("ide gaaaaas")
          }
        })
        .catch(error => {
          console.log(error);
        });


        axios.get("/Web/rest/amenities/all")
        .then(response => {
          this.amenities = response.data;
          if(response.status == 200) {
            console.log("ide gaaaaas")
          }
        })
        .catch(error => {
          console.log(error);
        });
    }
}
</script>
