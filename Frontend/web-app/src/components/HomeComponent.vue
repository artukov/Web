<template>
  <div>
    <div>
      <b-button class="searchBox" v-b-modal.modal-1>Search</b-button>

      <b-modal id="modal-1" title="Search Box">
        <p class="my-4">Treba napravit formu za search</p>
      </b-modal>
    </div>
    <div class="content">
      <ul class="ul-content">
        <li v-for="apartment in info" :key="apartment.id">
          <div>
            <b-card
              img-src="https://picsum.photos/600/300/?image=25"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem;"
              class="mb-2"
            >
              <b-card-text>
                <div v-if="apartment.apartmentLocation.adress">
                  <b>
                    {{ apartment.apartmentLocation.adress.street }}
                    {{ apartment.apartmentLocation.adress.number }}
                  </b>
                  <div>
                    {{ apartment.apartmentLocation.adress.city }}
                    {{ apartment.apartmentLocation.adress.postalCode }}
                  </div>
                  <div>
                    {{ apartment.apartmentLocation.longitude }} ,
                    {{ apartment.apartmentLocation.latitude }}
                  </div>
                </div>
              </b-card-text>

              <b-button href="#" variant="primary">Open</b-button>
            </b-card>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "HomeContentComponent",
  mounted: function() {
    axios
      .get("http://localhost:8080/RACompany/rest/apartment/helloWorld")
      .then((response) => (this.info = response.data));
  },
  data() {
    return {
      info: null,
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.searchBox {
  margin: 20px;
}
.content {
  display: inline;
}
.ul-content li {
  display: inline-flex;
  margin: 15px;
}
</style>
