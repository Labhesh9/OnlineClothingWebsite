import React, { useEffect, useState } from "react";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";
import Item from "../../components/Item";
import { Typography } from "@mui/material";
import useMediaQuery from "@mui/material/useMediaQuery";
import { useDispatch, useSelector } from "react-redux";
import { setItems, loadItemsCart } from "../../state";

const ShoppingList = () => {
  const dispatch = useDispatch();
  const [value, setValue] = useState("all");
  const items = useSelector((state) => state.cart.items);
  const breakPoint = useMediaQuery("(min-width:600px)");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  async function getItems() {
    const items = await fetch(
      "http://localhost:4000/api/products/getAllProductWithPrice",
      { method: "GET" }
    );
    const itemsJson = await items.json();
    dispatch(setItems(itemsJson.data));
  }

  function getCarts() {
    var carts = JSON.parse(sessionStorage.getItem("cart"));
    console.log("carts");
    //console.log(carts);
    if (carts !== null){
       dispatch(loadItemsCart(carts));
       carts.map((m)=> console.log(m)
       )
    }
   
  }

  //console.log(items)
  useEffect(() => {
    getItems();
    getCarts();
  }, []); // eslint-disable-line react-hooks/exhaustive-deps

  const menItems = items.filter(
    (item) => item.agetypesId === 1
  );
  const ladiesItems = items.filter(
    (item) => item.agetypesId === 6
  );
  const babyItems = items.filter(
    (item) => item.agetypesId === 3
  );
  const kidItems = items.filter(
    (item) => item.agetypesId === 4
  );
  const sportItems = items.filter(
    (item) => item.agetypesId === 5
  );

  return (
    <Box width="80%" margin="80px auto">
      <Typography variant="h3" textAlign="center">
        Our Featured <b>Products</b>
      </Typography>
      <Tabs
        textColor="primary"
        indicatorColor="primary"
        value={value}
        onChange={handleChange}
        centered
        TabIndicatorProps={{ sx: { display: breakPoint ? "block" : "none" } }}
        sx={{
          m: "25px",
          "& .MuiTabs-flexContainer": {
            flexWrap: "wrap",
          },
        }}
      >
        <Tab label="ALL" value="all" />
        <Tab label="MEN" value="men" />
        <Tab label="LADIES" value="ladies" />
        <Tab label="BABY" value="baby" />
        <Tab label="KIDS" value="kids" />
        <Tab label="SPORTS" value="sports" />
      </Tabs>
      <Box
        margin="0 auto"
        display="grid"
        gridTemplateColumns="repeat(auto-fill, 300px)"
        justifyContent="space-around"
        rowGap="20px"
        columnGap="1.33%"
      >
        {value === "all" &&
          items.map((item) => (
            <Item item={item} key={`${item.name}-${item.id}`} />
          ))}
        {value === "ladies" &&
          ladiesItems.map((item) => (
            <Item item={item} key={`${item.name}-${item.id}`} />
          ))}
        {value === "baby" &&
          babyItems.map((item) => (
            <Item item={item} key={`${item.name}-${item.id}`} />
          ))}
        {value === "men" &&
          menItems.map((item) => (
            <Item item={item} key={`${item.name}-${item.id}`} />
          ))}
          {value === "kids" &&
          kidItems.map((item) => (
            <Item item={item} key={`${item.name}-${item.id}`} />
          ))}
           {value === "sports" &&
          sportItems.map((item) => (
            <Item item={item} key={`${item.name}-${item.id}`} />
          ))}
      </Box>
    </Box>
  );
};

export default ShoppingList;
