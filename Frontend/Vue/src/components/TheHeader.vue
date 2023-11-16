<template>
    <header>
        <nav>
            <ul>
                <li>
                  <router-link
                    class="nav-link"
                    active-class="active"
                    exact
                    :to="{ name: 'home' }">
                    Home
                  </router-link>
                </li>
                <li>
                  <router-link
                    class="nav-link"
                    active-class="active"
                    exact
                    :to="{ name: 'pistas' }">
                    Pistas
                  </router-link>
                </li>
                <li>
                  <router-link
                    class="nav-link"
                    active-class="active"
                    exact
                    :to="{ name: 'sports' }">
                    Sports
                  </router-link>
                </li>
            </ul>
        </nav>
    </header>
</template>

<script>
</script>

<style>
header {
    background-color: #333;
    color: #fff;
    padding: 10px;
    text-align: center;
}

nav ul {
    list-style: none;
    padding: 0;
    margin: 0;
}

nav ul li {
    display: inline;
    margin-right: 20px;
}

nav a {
    text-decoration: none;
    color: #fff;
    font-weight: bold;
}
</style>