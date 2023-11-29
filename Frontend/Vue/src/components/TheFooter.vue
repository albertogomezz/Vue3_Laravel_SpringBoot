<template>
    <!-- FOOTER START -->
    <div class="footer">
      <div class="contain">
      <div class="col">
        <h1>Company</h1>
        <ul>
          <li>About</li>
          <li>Mission</li>
          <li>Services</li>
          <li>Social</li>
          <li>Get in touch</li>
        </ul>
      </div>
      <div class="col">
        <h1>Products</h1>
        <ul>
          <li>About</li>
          <li>Mission</li>
          <li>Services</li>
          <li>Social</li>
          <li>Get in touch</li>
        </ul>
      </div>
      <div class="col">
        <h1>Accounts</h1>
        <ul>
          <li>About</li>
          <li>Mission</li>
          <li>Services</li>
          <li>Social</li>
        </ul>
      </div>
      <div class="col">
        <h1>Resources</h1>
        <ul>
          <li>Webmail</li>
          <li>Redeem code</li>
          <li>WHOIS lookup</li>
          <li>Site map</li>
        </ul>
      </div>
      <div class="col">
        <h1>Support</h1>
        <ul>
          <li>Contact us</li>
          <li>Web chat</li>
          <li>Open ticket</li>
        </ul>
      </div>
      <div class="col social">
        <h1>Social</h1>
        <ul>
          <li><h6>Kiko Gilabert</h6><a href="https://github.com/kikogilabert"><img src="../assets/github-color.svg" width="32" style="width: 32px;"></a></li>
          <li><h6>Alberto Gómez</h6><a href="https://github.com/albertogomezz"><img src="../assets/github-color.svg" width="32" style="width: 32px;"></a></li>
        </ul>
  
      </div>
    <div class="clearfix"></div>
    </div>
    </div>
    <!-- END OF FOOTER -->
</template>
<style>
.footer {
  margin-top: 100px;
  bottom: 0;
  width: 100%;
  position: relative;
  height: auto;
  background-color: #070617;
}
.footer .col {
  width: 190px;
  height: auto;
  float: left;
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  padding: 0px 20px 20px 20px;
}
.footer .col h1 {
  margin: 0;
  padding: 0;
  font-family: inherit;
  font-size: 12px;
  line-height: 17px;
  padding: 20px 0px 5px 0px;
  color: rgba(255,255,255,0.2);
  font-weight: normal;
  text-transform: uppercase;
  letter-spacing: 0.250em;
}
.footer .col ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}
.footer .col ul li {
  color: #999999;
  font-size: 14px;
  font-family: inherit;
  font-weight: bold;
  padding: 5px 0px 5px 0px;
  cursor: pointer;
  transition: .2s;
  -webkit-transition: .2s;
  -moz-transition: .2s;
}
.social ul li {
  display: inline-block;
  padding-right: 5px !important;
}

.footer .col ul li:hover {
  color: #ffffff;
  transition: .1s;
  -webkit-transition: .1s;
  -moz-transition: .1s;
}
.clearfix {
  clear: both;
}
@media only screen and (min-width: 1280px) {
  .contain {
    width: 1200px;
    margin: 0 auto;
  }
}
@media only screen and (max-width: 1139px) {
  .contain .social {
    width: 1000px;
    display: block;
  }
  .social h1 {
    margin: 0px;
  }
}
@media only screen and (max-width: 950px) {
  .footer .col {
    width: 33%;
  }
  .footer .col h1 {
    font-size: 14px;
  }
  .footer .col ul li {
    font-size: 13px;
  }
}
@media only screen and (max-width: 500px) {
    .footer .col {
      width: 50%;
    }
    .footer .col h1 {
      font-size: 14px;
    }
    .footer .col ul li {
      font-size: 13px;
    }
}
@media only screen and (max-width: 340px) {
  .footer .col {
    width: 100%;
  }
}
</style>