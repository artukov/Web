<template>
    <div>
        Idemoo

        <b-button @click="getApartments">Idemooo</b-button>
    </div>
</template>

<script>

import axios from "axios";
export default {
    data() {
        return {
            apartments: [],
            activeApartments: [],
            inactiveApartments: []
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

        axios.get("/Web/rest/apartment/hostAllActive/uganda")
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
    }
}
</script>