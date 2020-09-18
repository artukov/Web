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
          <input type="date" id="Form-availableFrom" class="form-control" min="2020-09-18" max="2021-09-18" v-model="date.availableFrom" required/>
          <label for="Form-DateTo">Leaving date</label>
          <input type="date" id="Form-availableTo" class="form-control" min="2020-09-18" max="2021-09-18" v-model="date.availableTo" required/>
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
                margin-top: -12%;">Apartments</h3>
            </div>
        </div>

        <div class="form-group" v-for="activeApartment in apartments" :key="activeApartment.id">
          
          <div class="header pt-3 grey lighten-2">
            <div class="row d-flex justify-content-start">
                <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                style="font-size: 25px;
                font-weight: 200;
                line-height: 1.2;">Apartment</h3>
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

                            <div class="text-center mb-4">
                                <button
                                type="button"
                                class="btn btn-primary btn-block z-depth-2"
                                @click="pickApartment(activeApartment)"
                                >Reserve apartment</button>
                            </div>

                            <div class="text-center mb-4">
                                <button
                                type="button"
                                class="btn btn-primary btn-block z-depth-2"
                                @click="pickForComment(activeApartment)"
                                >Comment on apartment</button>
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

            <div>
            <div class="form-group" v-for="comment in activeApartment.comments" :key="comment.text">
                <div class="header pt-3 grey lighten-2">
                  <div class="row d-flex justify-content-start">
                      <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                      style="font-size: 25px;
                      font-weight: 200;
                      line-height: 1.2;">Comment</h3>
                  </div>
                </div>
                <div class="card-body mx-4 mt-4">
                    <div class="row">
                        <div class="col">
                            <div class="md-form">
                                <label for="Form-type">Guest</label>
                                <label id="Form-type" class="form-control">{{comment.guest}}</label>

                                <label for="Form-rooms">Grade</label>
                                <label id="Form-rooms" class="form-control">{{comment.grade}}</label>

                                <label for="Form-guests">Comment</label>
                                <label id="Form-guests" class="form-control">{{comment.text}}</label>
                                <br/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
          </div>

        </div>   
    </div>
    </div>





    <div class="child d-flex justify-content-left" style="margin-top: 20px" v-if="!showAll && showReservation">
    
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
                            <input type="date" id="Form-availableFrom" class="form-control" v-model="formReservation.startDate" readonly/>

                            <label for="Form-guests">Number of days</label>
                            <input id="Form-guests" type="number" class="form-control" v-model="formReservation.numberNights" readonly>

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










    <div class="child d-flex justify-content-left" style="margin-top: 20px" v-if="!showAll && showComment">
    
    <div class="card" style="width: 50%">
      
        <div class="header pt-3 grey lighten-2">
            <div class="row d-flex justify-content-start">
                <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                style="font-size: 2rem;
                font-weight: 300;
                line-height: 1.2;
                margin-top: -12%;">Apartment for commenting</h3>
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
                margin-top: -12%;">Comment</h3>
            </div>
        </div>


                        <div class="md-form">
                            <label for="Form-rooms">Comment</label>
                            <!-- <label id="Form-rooms" class="form-control" v-if="date.availableFrom != 'null'">{{date.availableFrom}}</label>
                            <label id="Form-rooms" class="form-control" v-if="date.availableFrom == 'null'"></label> -->
                            <input type="text" id="Form-availableFrom" class="form-control" v-model="formComment.text" required/>

                            <label class="Form-surname">Value the apartment with a grade</label><br/>
                            <!-- <input type="radio" value="1" id="1" name="grade">
                            <label class="Form-surname">1</label><br/>
                            <input type="radio" value="2" id="2" name="grade">
                            <label class="Form-surname">2</label><br/>
                            <input type="radio" value="3" id="3" name="grade">
                            <label class="Form-surname">3</label><br/>
                            <input type="radio" value="4" id="4" name="grade">
                            <label class="Form-surname">4</label><br/>
                            <input type="radio" value="5" id="5" name="grade">
                            <label class="Form-surname">5</label>                            
                            <br/> -->

                            <div>
                              <select v-model="formComment.grade">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option selected>5</option>
                              </select>
                            </div>
                        </div>
                            <div class="text-center mb-4" style="padding: 20px">
                                <button
                                type="button"
                                class="btn btn-primary btn-block z-depth-2"
                                @click="comment()"
                                >Leave comment</button>
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
            formComment: {
              text: "",
              grade: 5,
              visible: true
            },
            types: ["ROOM", "COMPLETE"],
            locations: [],
            amenities: [],
            comments: [],
            localFields: { text: 'name' },
            address: "",
            showAll: true,
            showReservation: false,
            showComment: false,
            activeApartment: "",
            reservations: [],
            error: false,
            errormessage: "",
            success: false,
            successmessages: ""  
        }
    },

    methods: {
      onSubmit() {
        this.showAll = true;
        for(let location in this.locations) {
          console.log("1");
          if(location.longitude == this.form.location.longitude) {
            this.address = location.address;
            console.log("2");
          }
        }
        this.end = this.date.availableTo - this.date.availableFrom;
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
          axios.get("Web/rest/apartment/days/" + this.date.availableFrom + "/" + this.date.availableTo) 
          .then(response => {
            this.formReservation.numberNights = response.data;
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
        this.showReservation = true;
        this.showComment = false;
        this.activeApartment = apartment;
        console.log(apartment);
        this.formReservation.startDate = this.date.availableFrom;
      },

      reserve() {
        this.formReservation.apartment = this.activeApartment;
        this.formReservation.guest = this.$store.state.user.data.username;
        this.showAll = false;
        this.showComment = false;
        this.showReservation = false;
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
      },

      pickForComment(apartment) {
        console.log(apartment);
        this.showAll = false;
        this.showComment = true;
        this.showReservation = false;
        this.activeApartment = apartment;
      },

      comment() {
        this.showAll = false;
        this.showComment = false;
        this.showReservation = false;
        axios.post("Web/rest/apartment/addComment/" + this.formComment.text + "/" + this.formComment.grade + "/" + this.$store.state.user.data.username + "/" + this.activeApartment.id)
        // , this.formComment, this.activeApartment
        .then(response => {
          this.comments = response.data;
          this.success = true;
          this.successmessages = "You have successfully commented an apartment."
          this.error = false;
        })
        .catch(error => {
          console.log(error);
          this.errormessage = "Error while commenting.";
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
        this.dateee = new Date();
        console.log(this.date);

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