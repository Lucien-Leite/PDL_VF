<template>
  <div class="hello">
    <br />
    <div id="file-drag-drop">
      <form ref="fileform"></form>
    </div>
    <br />
    <br />
    <table>
      <tr>
        <td><span id="basicTxt">Image selectionnée</span></td>
        <td>
          <img
            v-bind:src="'/images/' + selectedImageId"
            key="selectedImageId"
            class="selectedImg"
            alt=""
            height="500"
            @click="clickOnImage(selectedImageId)"
          />
        </td>
        <td>
          <br />
          <br />
          <button class="btn" @click="alert('post')">Publier une image</button>
          <br />
          <button class="btn" @click="callDelete(selectedImageId)">
            Supprimer l'image
          </button>
        </td>
      </tr>
    </table>

    <div class="slt" v-for="image in images" v-bind:key="image.id">
      <img
        v-bind:src="'/images/' + image.id"
        class="lazy"
        v-bind:alt="image.name"
        width="100"
        @click="clickOnImage(image.id)"
      />
    </div>

    <table>
      <div class="slt" v-for="image in images" v-bind:key="image.id">
        <tbody>
          <tr>
            <img
              v-bind:src="'/images/' + image.id"
              class="lazy"
              v-bind:alt="image.name"
              width="100"
              @click="clickOnImage(image.id)"
            />
          </tr>
        </tbody>
      </div>
    </table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "HelloWorld",
  props: {
    msg: String,
  },

  data() {
    return {
      response: [],
      errors: [],
      selectedUser: "",
      images: [],
      selectedImageId: 0,
      dragAndDropCapable: false,
      files: [],
    };
  },

  mounted: function () {
    this.callGetList();
  },

  methods: {
    callGetList() {
      axios
        .get("images")
        .then((response) => {
          this.images = response.data;
          this.selectedImageId = this.images[0].id;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },

    callDelete(id) {
      axios
        .delete("images/" + id)
        .then((response) => {
          this.images = response.data;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },

    clickOnImage(id) {
      this.selectedImageId = id;
    },

    callPost(id) {
      axios
        .post("images/" + id)
        .then((response) => {
          this.images = response.data;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#basicTxt {
  color: #009fe3;
  font-weight: bold;
  font-size: 20px;
}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
table {
  margin-left: auto;
  margin-right: auto;
  padding: 30px;
}
td {
  padding: 20px;
}
.btn {
  background-color: #009fe3;
  font-size: 17px;
  border-radius: 28px;
  border: 1px solid #009fe3;
  font-family: Arial;
  color: white;
  padding: 10px;
  margin-bottom: 11px;
}
.selectedImg {
  border: 5px solid #009fe3;
}
</style>
