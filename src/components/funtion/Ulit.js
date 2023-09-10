export const findUpdateCartByProductIDInDataBase = (item) => {
  var helper = new XMLHttpRequest();
  helper.onreadystatechange = () => {
    if (helper.readyState === 4 && helper.status === 200) {
      var result = JSON.parse(helper.responseText);
      if (result.status === "success") {
        // console.log("Found");
        // console.log(result.data);
        item.id = result.data.id;
        item.quantity = item.quantity + result.data.quantity;
        updateCartToDataBase(item);
      } else {
        // console.log("Not Found");
        addCartToDataBase(item);
      }
    }
  };

  const url = `http://localhost:4000/api/shopcarts/getbyproductid/${item.productId}`;
  console.log(url);
  helper.open("GET", url);
  helper.setRequestHeader("content-type", "application/json");
  // debugger;
  helper.send();
};

export const deleteShopCartFromDataBase = () => {
  console.log("item");
  //console.log(item);
  var userId = sessionStorage.getItem("uid");
  //debugger;
  //console.log(item);
  //var isUserLoggedIn = sessionStorage.getItem("isUserLoggedIn");

  //if (isUserLoggedIn != null && isUserLoggedIn === "true") {
  var helper = new XMLHttpRequest();
  helper.onreadystatechange = () => {
    if (helper.readyState === 4 && helper.status === 200) {
      var result = JSON.parse(helper.responseText);
    }
  };
  const url = `http://localhost:4000/api/shopcarts/${userId}`;
  helper.open("DELETE", url);
  //helper.setRequestHeader("content-type", "application/json");
  // debugger;
  helper.send();
  //}
};

export const addCartToDataBase = (item) => {
  console.log("item");
  //console.log(item);
  var userId = sessionStorage.getItem("uid");

  item = { ...item, userId };

  console.log(item);
  var isUserLoggedIn = sessionStorage.getItem("isUserLoggedIn");

  if (isUserLoggedIn != null && isUserLoggedIn === "true") {
    var helper = new XMLHttpRequest();
    helper.onreadystatechange = () => {
      if (helper.readyState === 4 && helper.status === 200) {
        var result = JSON.parse(helper.responseText);
      }
    };
    helper.open("POST", "http://localhost:4000/api/shopcarts");
    helper.setRequestHeader("content-type", "application/json");
    // debugger;
    helper.send(JSON.stringify(item));
  }
};

export const addShopCartToOrder = (item) => {
  //var userId = sessionStorage.getItem("uid");
  const orderDetailItem = {
    orderId: 0,
    productId: 0,
    quantity: 0,
    realPrice: 0,
  };
  //item = { ...item, userId };

  console.log("Item orderproduct");
  console.log(item);
  // var isUserLoggedIn = sessionStorage.getItem("isUserLoggedIn");

  //if (isUserLoggedIn != null && isUserLoggedIn === "true") {
  var helper = new XMLHttpRequest();
  helper.onreadystatechange = () => {
    if (helper.readyState === 4 && helper.status === 200) {
      var result = JSON.parse(helper.responseText);
      if (result.status === "success") {
        // var result = JSON.parse(helper.responseText);

        for (var i = 0; i < result.data.length; i++) {
          orderDetailItem.orderId = item.orderId;
          orderDetailItem.productId = result.data[i].productId;
          orderDetailItem.realPrice = result.data[i].originalPrice;
          orderDetailItem.quantity = result.data[i].quantity;

          console.log("orderDetailItem");
          console.log(orderDetailItem);

          addOrderDetailToDataBase(orderDetailItem);
          deleteShopCartFromDataBase();
        }
      }
    }
  };
  const url = `http://localhost:4000/api/shopcarts/getbyuserid/${item.userId}`;
  helper.open("GET", url);
  helper.setRequestHeader("content-type", "application/json");
  // debugger;
  helper.send();
  //}
};

export const addOrderDetailToDataBase = (item) => {
  //var userId = sessionStorage.getItem("uid");
  //   const orderDetailItem = {
  //     orderId: 0,
  //     productId: 0,
  //     quantity: 0,
  //     realPrice: 0
  //   }
  // //item = { ...item, userId };

  //console.log(item);
  // var isUserLoggedIn = sessionStorage.getItem("isUserLoggedIn");

  //if (isUserLoggedIn != null && isUserLoggedIn === "true") {
  var helper = new XMLHttpRequest();
  helper.onreadystatechange = () => {
    if (helper.readyState === 4 && helper.status === 200) {
      // var result = JSON.parse(helper.responseText);
      // if (result.status === "success") {
      // } else {
      // }
    }
  };
  helper.open("POST", "http://localhost:4000/api/orderProductDetails");
  helper.setRequestHeader("content-type", "application/json");
  // debugger;
  helper.send(JSON.stringify(item));
  //}
};

export const addOrderToDataBase = (item) => {
  var userId = sessionStorage.getItem("uid");

  item = { ...item, userId };
  console.log("item add to products:");
  console.log(item);
  //console.log(item);
  //var isUserLoggedIn = sessionStorage.getItem("isUserLoggedIn");
  debugger;
  //if (isUserLoggedIn != null && isUserLoggedIn === "true") {
  var helper = new XMLHttpRequest();
  helper.onreadystatechange = () => {
    if (helper.readyState === 4 && helper.status === 200) {
      var result = JSON.parse(helper.responseText);
      if (result.status === "success") {
        var orderId = result.data.insertId;
        console.log("item add to products:");
        console.log(orderId);
        sessionStorage.setItem("orderId", orderId);
        item = { ...item, orderId };
        console.log("item add to products:");
        console.log(item);

        addShopCartToOrder(item);
      }
    }
  };
  helper.open("POST", "http://localhost:4000/api/orderProducts");
  helper.setRequestHeader("content-type", "application/json");
  // debugger;
  helper.send(JSON.stringify(item));
  //}
};

export const updateCartToDataBase = (item) => {
  console.log("item");
  //console.log(item);
  var userId = sessionStorage.getItem("uid");

  item = { ...item, userId };

  console.log(item);
  var isUserLoggedIn = sessionStorage.getItem("isUserLoggedIn");

  if (isUserLoggedIn != null && isUserLoggedIn === "true") {
    var helper = new XMLHttpRequest();
    helper.onreadystatechange = () => {
      if (helper.readyState === 4 && helper.status === 200) {
        var result = JSON.parse(helper.responseText);
      }
    };
    helper.open("PUT", "http://localhost:4000/api/shopcarts");
    helper.setRequestHeader("content-type", "application/json");
    // debugger;
    helper.send(JSON.stringify(item));
  }
};

export const loadCartFromDataBase = () => {
  console.log("Load data base start");
  //console.log(item);
  debugger;
  var userId = sessionStorage.getItem("uid");
  var isUserLoggedIn = sessionStorage.getItem("isUserLoggedIn");

  if (isUserLoggedIn != null && isUserLoggedIn === "true") {
    var helper = new XMLHttpRequest();
    helper.onreadystatechange = () => {
      if (helper.readyState === 4 && helper.status === 200) {
        var result = JSON.parse(helper.responseText);
        sessionStorage.setItem("cart", JSON.stringify(result.data));
      }
    };
    const url = `http://localhost:4000/api/shopcarts/getbyuserid/${userId}`;
    console.log(url);
    helper.open("GET", url);
    //helper.setRequestHeader("content-type", "application/json");
    // debugger;
    helper.send();
  }

  var carts = JSON.parse(sessionStorage.getItem("cart"));
  console.log(carts);
  console.log("Load data base end");
};

// function getCarts() {
//   var carts = JSON.parse(sessionStorage.getItem("cart"));
//   console.log("carts");
//   //console.log(carts);
//   if (carts !== null) {
//     dispatch(loadItemsCart(carts));
//     //  carts.map((m)=> console.log(m)
//     //  )
//   }
// }
