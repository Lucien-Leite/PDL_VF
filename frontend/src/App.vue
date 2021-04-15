<template>
  <div id="nav">
    <router-link to="/">Accueil</router-link> |
    <router-link to="/about">À propos</router-link>
  </div>
  <router-view />
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: white;
}

#nav {
  padding: 30px;
  background-color: rgba(0, 159, 227, 1);
}

#nav a {
  font-weight: bold;
  color: white;
}

#nav a.router-link-exact-active {
  color: rgb(0, 63, 90);
}
</style>


