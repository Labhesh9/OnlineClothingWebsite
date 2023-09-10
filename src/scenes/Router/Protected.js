import React from "react";
import { useNavigate, useLocation, Navigate } from "react-router-dom";
import Login from "../home/Login";

const Protected = ({ roles = [], children }) => {
  const navigate = useNavigate();
  const location = useLocation();
  var role = "guest";
  var userId = sessionStorage.getItem("uid");
  //var role = sessionStorage.getItem("uid");
  //var role = "user";
  var isUserLoggedIn = sessionStorage.getItem("isUserLoggedIn");
  // var loggedInUserName = sessionStorage.getItem('username');
  //debugger;
  if (isUserLoggedIn != null && isUserLoggedIn === "true") {
    if (location.pathname.startsWith("/admin")) {
      role = sessionStorage.getItem("role");
      // var helper1 = new XMLHttpRequest();
      // helper1.onreadystatechange = () => {
      //   if (helper1.readyState === 4 && helper1.status === 200) {
      //     var result = JSON.parse(helper1.responseText);
      //     if (result.status === "success") {
      //       role = result.data.roleid;
      //     }
      //   }
      // };
      // const url = `http://localhost:4000/api/users/${userId}`;
      // helper1.open("GET", url);
      // helper1.setRequestHeader("content-type", "application/json");
      // // debugger;
      // helper1.send();

      return !roles.length || roles.includes(role) ? (
        children
      ) : (
        <Navigate to="/" replace />
      );
    } else {
      return children;
    }
  } else {
    return <Navigate to="/login" replace />;
  }

  //console.log(location);

  //   return children;
};

export default Protected;
