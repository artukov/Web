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
        <b-form-select v-model="form.location">
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

      <b-form-group id="input-group-1" label="Amenities:" label-for="input-1">
        <!-- <b-form-select multiple v-model="selectedLocation">
          <option
            v-for="location in locations"
            :value="location.longitude"
            :key="location.longitude">
            {{location.longitude}}, {{location.latitude}}, {{location.address.city}}
          </option>
        </b-form-select> -->
        <!-- <multiselect v-model="selectedAmenities" :options="locations
        " track-by="longitude" multiple></multiselect> -->
        <ejs-multiselect :dataSource="amenities" :fields="localFields" v-model="form.amenities" placeholder="Select an amenity" popupWidth="200px" popupHeight="250px">
          </ejs-multiselect>
      </b-form-group>


      

      <b-button type="submit" variant="primary">Register</b-button>
      <b-button @click="idemo" variant="primary">Idemo</b-button>
    </b-form>

    <!-- <div>
        <div style="margin:10% 25%">
          <br> -->
          
        <!-- </div>
      </div> -->
  </div>
    
</template>

<script>
// import Multiselect from 'vue-multiselect'
import axios from "axios";
import Vue from 'vue';
import { MultiSelectPlugin } from '@syncfusion/ej2-vue-dropdowns'
Vue.use(MultiSelectPlugin);
export default {
  components: {
    //  Multiselect
    // MultiSelectPlugin
      },
    data() {
        return {
            form: {
                apartmentType: "",
                numberRooms: "",
                guestNumber: "",
                location: {},
                // appartmentDates: null,
                host: this.$store.state.user,
                // comments: null,
                priceNight: "",
                checkIn: "14:00",
                checkOut: "10:00",
                appStatus: false,
                amenities: [],
                // reservations: null
            },
            types: ["ROOM", "COMPLETE"],
            selectedLocation: "",
            locations: [],
            amenities: [],
            apartments: [],
            localFields: { value: 'id', text: 'name' },
            selectedAmenities: null,
            reservations: ""
        }
    },

    methods: {
      idemo() {
        axios.get("/Web/rest/apartment/all")
        .then(response => {
          this.locations = response.data;
          if(response.status == 200) {
            console.log("ide gaaaaas")
          }
        })
        .catch(error => {
          console.log(error);
        });
      },

      onSubmit() {
        axios.post("/Web/rest/apartment/new")
        .then(response => {
          this.form = response.data;
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

<style>
  @import url(https://cdn.syncfusion.com/ej2/material.css);
</style>