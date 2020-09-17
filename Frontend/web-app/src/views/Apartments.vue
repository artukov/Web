<template>
    <div class="container">


        <div class="position">

    <b-container v-if="error">
      <b-alert show variant="danger" class="d-flex justify-content-center">{{errormessage}}</b-alert>
    </b-container>

     <b-container v-if="success">
      <b-alert show variant="success" class="d-flex justify-content-center">{{successmessages}}</b-alert>
    </b-container>

    <b-form @submit.prevent="onSubmit" method="get">

      <b-form-group id="input-group-2" label="Number of rooms:" label-for="input-2">
        <b-form-input
          id="input-2"
          type="number"
          v-model="form.numberRooms"
          placeholder="Enter number of rooms"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Number of guests:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.guestNumber"
          type="number"
          placeholder="Enter number of guests"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-1" label="Location:" label-for="input-1">
        <b-form-select v-model="form.location">
          <option
            v-for="location in locations"
            :value="location.longitude"
            :key="location.longitude">
            {{location.address.city}}
          </option>
        </b-form-select>
      </b-form-group>

      <b-form-group id="input-group-5" label-for="input-5">
          <label for="Form-DateFrom">Pickup date</label>
          <input type="date" id="Form-availableFrom" class="form-control" v-model="date.availableFrom" />
          <label for="Form-DateTo">Leaving date</label>
          <input type="date" id="Form-availableTo" class="form-control" v-model="date.availableTo" />
      </b-form-group>

      <b-form-group id="input-group-3" label="Price per night:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.priceNight"
          type="number"
          placeholder="Enter price"
        ></b-form-input>
      </b-form-group>

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

      <b-button type="submit" variant="primary">Search apartments</b-button>
    </b-form>

    <!-- <div>
        <div style="margin:10% 25%">
          <br> -->
          
        <!-- </div>
      </div> -->
  </div>



    <!-- <b-container v-if="error">
      <b-alert show variant="danger" class="d-flex justify-content-center">{{errormessage}}</b-alert>
    </b-container>

     <b-container v-if="success">
      <b-alert show variant="success" class="d-flex justify-content-center">{{successmessages}}</b-alert>
    </b-container> -->

    <div class="child d-flex justify-content-left" style="margin-top: 20px" v-if="showAll">
    
    <div class="card" style="width: 50%">
      
        <div class="header pt-3 grey lighten-2">
            <div class="row d-flex justify-content-start">
                <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                style="font-size: 2rem;
                font-weight: 300;
                line-height: 1.2;
                margin-top: -12%;">Active apartments</h3>
            </div>
        </div>

        <div class="form-group" v-for="activeApartment in apartments" :key="activeApartment.id">
            <div class="card-body mx-4 mt-4">
                <div class="row">
                    <div class="col">
                        <div class="md-form">
                            <label for="Form-rooms">Number of rooms</label>
                            <label id="Form-rooms" class="form-control">{{activeApartment.numberRooms}}</label>

                            <label for="Form-guests">Number of guests</label>
                            <label id="Form-guests" class="form-control">{{activeApartment.guestNumber}}</label>

                            <!-- <label for="Form-surname">Location</label>
                            <label id="Form-surname" class="form-control">{{activeApartment.location}}</label> -->

                            <div v-for="location in locations"  :value="location.longitude"
            :key="location.longitude">
                              <div v-if="location.longitude == form.location || form.location == ''">
                                <label for="Form-surname">Location</label>
                                <label id="Form-surname" class="form-control">{{location.address.city}}, {{location.address.street}} {{location.address.number}}</label>
                              </div>
                            </div>



                            <label for="Form-surname">Price per night</label>
                            <label id="Form-surname" class="form-control">{{activeApartment.priceNight}}</label>
                            <br/>

                            <div class="text-center mb-4">
                                <button
                                type="button"
                                class="btn btn-primary btn-block z-depth-2"
                                @click="pickApartment(activeApartment)"
                                >Reserve apartment</button>
                            </div>
                            
                            <!-- <div v-if="client.blocked" class="text-center mb-4">
                                <button
                                type="button"
                                class="btn btn-secondary btn-block z-depth-2"
                                @click="unblock(client.id)"
                                >Unblock</button>
                            </div> -->
                        </div>
                    </div>
                </div>
            </div>
        </div>   
    </div>
    </div>





    <div class="child d-flex justify-content-left" style="margin-top: 20px" v-if="!showAll">
    
    <div class="card" style="width: 50%">
      
        <div class="header pt-3 grey lighten-2">
            <div class="row d-flex justify-content-start">
                <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                style="font-size: 2rem;
                font-weight: 300;
                line-height: 1.2;
                margin-top: -12%;">Apartment for reservation</h3>
            </div>
        </div>

            <div class="card-body mx-4 mt-4">
                <div class="row">
                    <div class="col">
                        <div class="md-form">
                            <label for="Form-rooms">Number of rooms</label>
                            <label id="Form-rooms" class="form-control">{{activeApartment.numberRooms}}</label>

                            <label for="Form-guests">Number of guests</label>
                            <label id="Form-guests" class="form-control">{{activeApartment.guestNumber}}</label>

                            <!-- <label for="Form-surname">Location</label>
                            <label id="Form-surname" class="form-control">{{activeApartment.location}}</label> -->

                            <div v-for="location in locations"  :value="location.longitude"
            :key="location.longitude">
                              <div v-if="location.longitude == form.location || form.location == ''">
                                <label for="Form-surname">Location</label>
                                <label id="Form-surname" class="form-control">{{location.address.city}}, {{location.address.street}} {{location.address.number}}</label>
                              </div>
                            </div>



                            <label for="Form-surname">Price per night</label>
                            <label id="Form-surname" class="form-control">{{activeApartment.priceNight}}</label>
                            <br/>





<div class="header pt-3 grey lighten-2">
            <div class="row d-flex justify-content-start">
                <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                style="font-size: 2rem;
                font-weight: 300;
                line-height: 1.2;
                margin-top: -12%;">Reservation</h3>
            </div>
        </div>


                        <div class="md-form">
                            <label for="Form-rooms">Reservation date</label>
                            <!-- <label id="Form-rooms" class="form-control" v-if="date.availableFrom != 'null'">{{date.availableFrom}}</label>
                            <label id="Form-rooms" class="form-control" v-if="date.availableFrom == 'null'"></label> -->
                            <input type="date" id="Form-availableFrom" class="form-control" v-model="formReservation.startDate" required/>

                            <label for="Form-guests">Number of days</label>
                            <input id="Form-guests" type="number" class="form-control" v-model="formReservation.numberNights" required>

                            <label for="Form-surname">Message for host</label>
                            <input id="Form-surname" class="form-control" v-model="formReservation.reservationMessage" required>
                            
                            <br/>
                        </div>
                            <div class="text-center mb-4">
                                <button
                                type="button"
                                class="btn btn-primary btn-block z-depth-2"
                                @click="reserve()"
                                >Reserve apartment</button>
                            </div>
                            
                            <!-- <div v-if="client.blocked" class="text-center mb-4">
                                <button
                                type="button"
                                class="btn btn-secondary btn-block z-depth-2"
                                @click="unblock(client.id)"
                                >Unblock</button>
                            </div> -->
                        </div>
                    </div>
                </div>
            </div>
    </div>
    </div>




    </div>
</template>

<script>

import axios from "axios";
export default {
    data() {
        return {
            apartments: [],
            form: {
                numberRooms: "",
                guestNumber: "",
                location: "",
                // // appartmentDates: null,
                // // comments: null,
                priceNight: "",
                appStatus: true,
                // // reservations: null
            },
            date: {
              availableFrom: "null",
              availableTo: "null"
            },
            formReservation: {
              apartment: "",
              startDate: "null",
              numberNights: "",
              // fullPrice: 5.0,
              reservationMessage: "",
              guest: "",
              // statusRes: "CREATED"
            },
            types: ["ROOM", "COMPLETE"],
            locations: [],
            amenities: [],
            localFields: { text: 'name' },
            address: "",
            showAll: true,
            activeApartment: "",
            reservations: [],
            error: false,
            errormessage: "",
            success: false,
            successmessages: "",
            
        }
    },

    methods: {
      onSubmit() {
        for(let location in this.locations) {
          console.log("1");
          if(location.longitude == this.form.location.longitude) {
            this.address = location.address;
            console.log("2");
          }
        }
        axios.post("Web/rest/apartment/search/" + this.date.availableFrom + "/" + this.date.availableTo, this.form) 
          .then(response => {
            this.apartments = response.data;
            if(response.status == 200) {
              console.log("ide gaaaaaaaas");
            }
          })
          .catch(error => {
            console.log(error);
          })
      },

      pickApartment(apartment) {
        this.showAll = false;
        this.activeApartment = apartment;
        console.log(apartment);
        this.formReservation.startDate = this.date.availableFrom;
      },

      reserve() {
        this.formReservation.apartment = this.activeApartment;
        this.formReservation.guest = this.$store.state.user.data.username;
        axios.post("Web/rest/addReservation/" + this.formReservation.startDate + "/" + this.formReservation.numberNights + "/" + this.formReservation.reservationMessage + "/" + this.formReservation.guest, this.activeApartment)
        .then(response => {
          this.reservations = response.data;
          console.log("ide gaaaaaaaaas");
          this.success = true;
          this.successmessages = "You have successfully reserved an apartment."
          this.error = false;
        })
        .catch(error => {
          console.log(error);
          this.errormessage = "Dates not available.";
          this.error = true;
      })
      }
    },

    mounted() {
        // axios.get("Web/rest/apartment/allActive")
        // .then(response => {
        //     this.apartments = response.data;
        //     if(response.status == 200) {
        //         console.log("ide gaaaaaaaaas");
        //     }
        // })
        // .catch(error => {
        //     console.log(error);
        // })


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