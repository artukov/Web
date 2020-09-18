<template>
    <div>

        <b-container v-if="error">
        <b-alert show variant="danger" class="d-flex justify-content-center">{{errormessage}}</b-alert>
        </b-container>

        <b-container v-if="success">
        <b-alert show variant="success" class="d-flex justify-content-center">{{successmessages}}</b-alert>
        </b-container>

        
        <b-form @submit.prevent="onSubmit" method="post">
        <b-form-group id="input-group-1" label="Amenity name:" label-for="input-1">
            <b-form-input
            id="input-2"
            v-model="form.name"
            placeholder="Enter amenity name"
            ></b-form-input>
        </b-form-group>
            <b-button type="submit" variant="primary">Add amenity</b-button>
        </b-form>



        <div class="child d-flex justify-content-left" style="margin-top: 20px" v-if="showAmenities">
        
        <div class="card" style="width: 40%">
        
            <div class="header pt-3 grey lighten-2">
                <div class="row d-flex justify-content-start">
                    <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                    style="font-size: 2rem;
                    font-weight: 300;
                    line-height: 1.2;
                    margin-top: -12%;">Amenities</h3>
                </div>
            </div>

            <div class="form-group" v-for="amenity in amenities" :key="amenity.name">
                <div class="card-body mx-4 mt-4">
                    <div class="row">
                        <div class="col">
                            <div class="md-form">
                                <label for="Form-type">Amenity name</label>
                                <input id="Form-type" class="form-control" readonly v-model="amenity.name">
                                <br/>

                                <!-- <label for="Form-type">New amenity name</label>
                                <input id="Form-type" class="form-control" v-bind="newAmenity">
                                <br/> -->

                                <div class="text-center mb-4">
                                    <button
                                    type="button"
                                    class="btn btn-primary btn-block z-depth-2"
                                    @click="showAmenity(amenity.name)"
                                    >Change amenity</button>
                                </div>

                                   <div class="text-center mb-4">
                                    <button
                                    type="button"
                                    class="btn btn-danger btn-block z-depth-2"
                                    @click="deleteAmenity(amenity.name)"
                                    >Delete amenity</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>


        <div class="child d-flex justify-content-left" style="margin-top: 20px" v-if="!showAmenities">
        
        <div class="card" style="width: 40%">
        
            <div class="header pt-3 grey lighten-2">
                <div class="row d-flex justify-content-start">
                    <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                    style="font-size: 2rem;
                    font-weight: 300;
                    line-height: 1.2;
                    margin-top: -12%;">Amenities</h3>
                </div>
            </div>

                <div class="card-body mx-4 mt-4">
                    <div class="row">
                        <div class="col">
                            <div class="md-form">
                                <label for="Form-type">Amenity name</label>
                                <input id="Form-type" class="form-control" readonly v-model="oldAmenity">
                                <br/>

                                <label for="Form-type">New amenity name</label>
                                <input id="Form-type" class="form-control" v-model="newAmenity">
                                <br/>

                                <div class="text-center mb-4">
                                    <button
                                    type="button"
                                    class="btn btn-primary btn-block z-depth-2"
                                    @click="changeAmenity()"
                                    >Change amenity</button>
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
            form: {
                name: ""
            },
            oldAmenity: "",
            newAmenity: "",
            amenities: [],
            error: false,
            errormessage: "",
            success: false,
            successmessages: "",
            showAmenities: true
        }
    },

    mounted() {
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
    },

    methods: {
        onSubmit() {
            axios.post("Web/rest/amenities/new", this.form) 
            .then(response => {
                console.log(response);
                this.success = true;
                this.successmessages = "Amenity successfully added."
                this.error = false;
            })
            .catch(error => {
            console.log(error);
            this.errormessage = "Error while adding amenity.";
            this.error = true;
          })
        },

        showAmenity(id) {
            this.oldAmenity = id;
            this.showAmenities = false;
        },

        changeAmenity() {
            axios.put("Web/rest/amenities/modify/" + this.oldAmenity + "/" + this.newAmenity)
            .then(response => {
                this.amenities = response.data;
                this.showAmenities = true;
                if(response.status == 200) {
                    console.log("ide gaaaaaaas");
                }
            })
            .catch(error => {
                console.log(error);
            })
        },

        deleteAmenity(amenity) {
            axios.delete("Web/rest/amenities/delete/" + amenity)
            .then(response => {
                this.amenities = response.data;
                this.showAmenities = true;
            })
            .catch(error => {
                console.log(error);
            })
        }
    }
}
</script>