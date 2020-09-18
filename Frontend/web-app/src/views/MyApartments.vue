<template>
  <div class="container">

    <b-container v-if="error">
      <b-alert show variant="danger" class="d-flex justify-content-center">{{errormessage}}</b-alert>
    </b-container>

     <b-container v-if="success">
      <b-alert show variant="success" class="d-flex justify-content-center">{{successmessages}}</b-alert>
    </b-container>

    <div class="child d-flex justify-content-left" style="margin-top: 20px">
    
    <div class="card" style="width: 70%">
      
        <div class="header pt-3 grey lighten-2">
            <div class="row d-flex justify-content-start">
                <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                style="font-size: 2rem;
                font-weight: 300;
                line-height: 1.2;
                margin-top: -12%;">Active apartments</h3>
            </div>
        </div>

        <div class="form-group" v-for="activeApartment in activeApartments" :key="activeApartment.id">
            <div class="card-body mx-4 mt-4">
                <div class="row">
                    <div class="col">
                        <div class="md-form">
                            <label for="Form-type">Apartment type</label>
                            <label id="Form-type" class="form-control">{{activeApartment.apartmentType}}</label>

                            <label for="Form-rooms">Number of rooms</label>
                            <label id="Form-rooms" class="form-control">{{activeApartment.numberRooms}}</label>

                            <label for="Form-guests">Number of guests</label>
                            <label id="Form-guests" class="form-control">{{activeApartment.guestNumber}}</label>

                            <label for="Form-surname">Location</label>
                            <label id="Form-surname" class="form-control">{{activeApartment.location}}</label>

                            <label for="Form-surname">Price per night</label>
                            <label id="Form-surname" class="form-control">{{activeApartment.priceNight}}</label>

                            <label for="Form-surname">Check in time</label>
                            <label id="Form-surname" class="form-control">{{activeApartment.checkIn}}</label>

                            <label for="Form-surname">Check out time</label>
                            <label id="Form-surname" class="form-control">{{activeApartment.checkOut}}</label>

                            <br/>

                            <div class="text-center mb-4">
                                <button
                                type="button"
                                class="btn btn-danger btn-block z-depth-2"
                                @click="Deactivate(activeApartment.id)"
                                >Deactivate</button>
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


    <div class="child d-flex justify-content-left" style="margin-top: 20px" float="right">
    
    <div class="card" style="width: 70%">
      
        <div class="header pt-3 grey lighten-2">
            <div class="row d-flex justify-content-start">
                <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                style="font-size: 2rem;
                font-weight: 300;
                line-height: 1.2;
                margin-top: -12%;">Inactive apartments</h3>
            </div>
        </div>

        <div class="form-group" v-for="inactiveApartment in inactiveApartments" :key="inactiveApartment.id">
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
                            <label for="Form-type">Apartment type</label>
                            <label id="Form-type" class="form-control">{{inactiveApartment.apartmentType}}</label>

                            <label for="Form-rooms">Number of rooms</label>
                            <label id="Form-rooms" class="form-control">{{inactiveApartment.numberRooms}}</label>

                            <label for="Form-guests">Number of guests</label>
                            <label id="Form-guests" class="form-control">{{inactiveApartment.guestNumber}}</label>

                            <label for="Form-surname">Location</label>
                            <label id="Form-surname" class="form-control">{{inactiveApartment.location}}</label>

                            <label for="Form-surname">Price per night</label>
                            <label id="Form-surname" class="form-control">{{inactiveApartment.priceNight}}</label>

                            <label for="Form-surname">Check in time</label>
                            <label id="Form-surname" class="form-control">{{inactiveApartment.checkIn}}</label>

                            <label for="Form-surname">Check out time</label>
                            <label id="Form-surname" class="form-control">{{inactiveApartment.checkOut}}</label>

                            <br/>

                            <div class="text-center mb-4">
                                <button
                                type="button"
                                class="btn btn-primary btn-block z-depth-2"
                                @click="Activate(inactiveApartment.id)"
                                >Activate</button>
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
          <div class="header pt-3 grey lighten-2">
              <div class="row d-flex justify-content-start">
                  <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                  style="font-size: 2rem;
                  font-weight: 300;
                  line-height: 1.2;
                  margin-top: -12%;">Comments</h3>
              </div>
          </div>

          <div>
            <div class="form-group" v-for="comment in inactiveApartment.comments" :key="comment.text">
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
  </div>
</template>

<script>

import axios from "axios";
export default {
    data() {
        return {
            apartments: [],
            activeApartments: [],
            inactiveApartments: [],
            error: false,
            errormessage: "",
            success: false,
            successmessages: "",
            comments: []
        }
    },

    methods: {
        getApartments() {
            axios.get("/Web/rest/apartment/hostAll/" + this.$store.state.user.data.username)
                .then(response => {
                    this.apartments = response.data;
                })
                .catch(error => {
                    console.log(error);
                })
        },

        Deactivate(id) {
          axios.put("/Web/rest/apartment/deactivate/" + id)
          .then(response => {
            console.log(id);
            if(response.status == 200) {
              console.log("ide gaaaaaas");
              this.success = true;
              this.successmessages = "Apartment successfully deactivated."
              this.error = false;
            } else {
              this.errormessage = "Error occurred while deactivating an apartment.";
              this.error = true;
            }
          })
          .catch(error => {
            console.log(error);
            this.errormessage = "Error occurred while deactivating an apartment.";
            this.error = true;
          })
        },

        Activate(id) {
          axios.put("/Web/rest/apartment/activate/" + id)
          .then(response => {
            console.log(id);
            if(response.status == 200) {
              console.log("ide gaaaaaas");
              this.success = true;
              this.successmessages = "Apartment successfully activated."
              this.error = false;
            } else {
              this.errormessage = "Error occurred while activating an apartment.";
              this.error = true;
            }
          })
          .catch(error => {
            console.log(error);
            this.errormessage = "Error occurred while activating an apartment.";
            this.error = true;
          })
        }
        
    },

    mounted() {
        // axios.get("/Web/rest/apartment/hostAll/" + this.$store.state.user.data.username)
        // .then(response => {
        //   this.apartments = response.data;
        //   if(response.status == 200) {
        //     console.log("ide gaaaaas")
        //   }
        // })
        // .catch(error => {
        //   console.log(error);
        // });

        axios.get("/Web/rest/apartment/hostAllActive/" + this.$store.state.user.data.username)
        .then(response => {
          this.activeApartments = response.data;
          if(response.status == 200) {
            console.log("ide gaaaaas")
          }
        })
        .catch(error => {
          console.log(error);
        });

        axios.get("/Web/rest/apartment/hostAllNotActive/" + this.$store.state.user.data.username)
        .then(response => {
          this.inactiveApartments = response.data;
          if(response.status == 200) {
            console.log("ide gaaaaas")
          }
        })
        .catch(error => {
          console.log(error);
        });


      // axios.post("/Web/rest/apartment/commentsHost/" + this.$store.state.user.data.username)
      // .then(response => {
      //   this.comments = response.data;
      // })
      // .then(error => {
      //   console.log(error);
      // })

    }
}
</script>

<style scoped>
  .child {
    float: left;
    width: 50%;
  }
</style>