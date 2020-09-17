<template>
<!-- v-if="this.$store.state.role == 'GUEST'" -->
    <div>

        <b-container v-if="error">
            <b-alert show variant="danger" class="d-flex justify-content-center">{{errormessage}}</b-alert>
        </b-container>

        <b-container v-if="success">
        <b-alert show variant="success" class="d-flex justify-content-center">{{successmessages}}</b-alert>
        </b-container>

        <div class="child d-flex justify-content-left" style="margin-top: 20px" >
            <div class="card" style="width: 70%">
            
                <div class="header pt-3 grey lighten-2">
                    <div class="row d-flex justify-content-start">
                        <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                        style="font-size: 2rem;
                        font-weight: 300;
                        line-height: 1.2;
                        margin-top: -12%;">My reservations</h3>
                    </div>
                </div>

                <div class="form-group" v-for="reservation in guestReservations" :key="reservation.id">
                    <div class="card-body mx-4 mt-4">
                        <div class="row">
                            <div class="col">
                                <div class="md-form">
                                    <label for="Form-type">Apartment type</label>
                                    <label id="Form-type" class="form-control">{{reservation.apartment.apartmentType}}</label>

                                    <label for="Form-rooms">Start date</label>
                                    <label id="Form-rooms" class="form-control">{{reservation.startDate}}</label>

                                    <label for="Form-guests">Number of nights</label>
                                    <label id="Form-guests" class="form-control">{{reservation.numberNights}}</label>

                                    <label for="Form-surname">Price</label>
                                    <label id="Form-surname" class="form-control">{{reservation.fullPrice}}</label>

                                    <label for="Form-surname">Reservation status</label>
                                    <label id="Form-surname" class="form-control">{{reservation.statusRes}}</label>
                                    <br/>

                                    <div class="text-center mb-4" v-if="reservation.statusRes == 'CREATED' || 'ACCEPTED'">
                                        <button
                                        type="button"
                                        class="btn btn-danger btn-block z-depth-2"
                                        @click="Withdraw(reservation.id)"
                                        >Withdraw</button>
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


        <div class="child d-flex justify-content-left" style="margin-top: 20px" v-if="this.$store.state.role == 'HOST'">
            <div class="card" style="width: 70%">
            
                <div class="header pt-3 grey lighten-2">
                    <div class="row d-flex justify-content-start">
                        <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                        style="font-size: 2rem;
                        font-weight: 300;
                        line-height: 1.2;
                        margin-top: -12%;">My reservations</h3>
                    </div>
                </div>

                <div class="form-group" v-for="reservation in hostReservations" :key="reservation.id">
                    <div class="card-body mx-4 mt-4">
                        <div class="row">
                            <div class="col">
                                <div class="md-form">
                                    <label for="Form-type">Apartment type</label>
                                    <label id="Form-type" class="form-control">{{reservation.apartment.apartmentType}}</label>

                                    <label for="Form-rooms">Start date</label>
                                    <label id="Form-rooms" class="form-control">{{reservation.startDate}}</label>

                                    <label for="Form-guests">Number of nights</label>
                                    <label id="Form-guests" class="form-control">{{reservation.numberNights}}</label>

                                    <label for="Form-surname">Price</label>
                                    <label id="Form-surname" class="form-control">{{reservation.fullPrice}}</label>

                                    <label for="Form-surname">Reservation status</label>
                                    <label id="Form-surname" class="form-control">{{reservation.statusRes}}</label>
                                    <br/>

                                    <div class="text-center mb-4">
                                        <button
                                        type="button"
                                        class="btn btn-danger btn-block z-depth-2"
                                        @click="Deactivate(reservation.id)"
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



        <div class="child d-flex justify-content-left" style="margin-top: 20px" v-if="this.$store.state.role == 'ADMIN'">
            <div class="card" style="width: 70%">
            
                <div class="header pt-3 grey lighten-2">
                    <div class="row d-flex justify-content-start">
                        <h3 class="deep-grey-text mt-3 mb-4 pb-1 mx-5"
                        style="font-size: 2rem;
                        font-weight: 300;
                        line-height: 1.2;
                        margin-top: -12%;">Reservations</h3>
                    </div>
                </div>

                <div class="form-group" v-for="reservation in reservations" :key="reservation.id">
                    <div class="card-body mx-4 mt-4">
                        <div class="row">
                            <div class="col">
                                <div class="md-form">
                                    <label for="Form-type">Apartment type</label>
                                    <label id="Form-type" class="form-control">{{reservation.apartment.apartmentType}}</label>

                                    <label for="Form-rooms">Start date</label>
                                    <label id="Form-rooms" class="form-control">{{reservation.startDate}}</label>

                                    <label for="Form-guests">Number of nights</label>
                                    <label id="Form-guests" class="form-control">{{reservation.numberNights}}</label>

                                    <label for="Form-surname">Price</label>
                                    <label id="Form-surname" class="form-control">{{reservation.fullPrice}}</label>

                                    <label for="Form-surname">Reservation status</label>
                                    <label id="Form-surname" class="form-control">{{reservation.statusRes}}</label>
                                    <br/>
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
            guestReservations: [],
            hostReservations: [],
            reservations: [],
            error: false,
            errormessage: "",
            success: false,
            successmessages: "",
        }
    },

    mounted() {
        axios.get("Web/rest/guestReservations/" + this.$store.state.user.data.username)
        .then(response => {
            this.guestReservations = response.data;
        })
        .catch(error => {
            console.log(error);
        }),

        axios.get("Web/rest/hostReservations/" + this.$store.state.user.data.username)
        .then(response => {
            this.hostReservations = response.data;
        })
        .catch(error => {
            console.log(error);
        }),

        axios.get("Web/rest/reservations")
        .then(response => {
            this.reservations = response.data;
        })
        .catch(error => {
            console.log(error);
        })
    },

    methods: {

        Withdraw(id) {
            axios.put("Web/rest/withdrawReservation/" + id + "/" + this.$store.state.user.data.username)
            .then(response => {
                this.guestReservations = response.data;
                this.success = true;
                this.successmessages = "You have successfully changed reservation status."
                this.error = false;
            })
            .catch(error => {
                this.errormessage = "Error occurred while changing reservation status.";
                this.error = true;
                console.log(error);
            })
        }

    }
}
</script>