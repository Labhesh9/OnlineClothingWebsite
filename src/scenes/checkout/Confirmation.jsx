import { Box, Button,  Typography,IconButton,Divider, TextField, FormControlLabel, Checkbox } from "@mui/material";
import { DataGrid } from '@mui/x-data-grid';
import styled from "@emotion/styled";
import Grid from '@mui/material/Grid';
import React, { useEffect, useState } from "react";
import { shades } from "../../theme";




export default function Confirmation() {
  var orderId = sessionStorage.getItem("orderId");
  var userId = sessionStorage.getItem("uid");
  const [orders, setOrders] = useState([]);
  const [order, setOrder] = useState();
  const [addressUser, setAddressUser] = useState([]);

  // const loadOrders = () =>{
  //     debugger
  //     var helper = new XMLHttpRequest();
  //     helper.onreadystatechange = () => {
  //       if (helper.readyState === 4 && helper.status === 200) {
  //         var result = JSON.parse(helper.responseText);
  //         setOrders(result.data);
  //       }
  //     };
  //     const url = `http://localhost:4000/api/orderProductDetails/orderid/${orderId}`;

  //     helper.open("GET", url);
  //     helper.send();
    
  // }

  async function loadOrders() {
    const items = await fetch(
      `http://localhost:4000/api/orderProductDetails/orderid/${orderId}`,
      { method: "GET" }
    );
    const itemsJson = await items.json();

    setOrders(itemsJson.data);
    setOrder(orders.slice(0,1))
    
  }

  async function loadAddress() {
    const items = await fetch(
      `http://localhost:4000/api/addressUsers/${userId}`,
      { method: "GET" }
    );
    const itemsJson = await items.json();
    setAddressUser(itemsJson.data);
  }



  useEffect(() => {
    loadOrders();
    
    loadAddress();
   // getCarts();
  }, [orders,addressUser]);

  

 

  const FlexBox = styled(Box)`
    display: flex;
  justify-content: space-between;
  align-items: center;
`;
const totalPrice = orders.reduce((total, item) => {
  return total + item.quantity * item.realPrice;
}, 0);
const columns = [
  { field: 'id', headerName: 'ID', width: 90 },
  {
    field: 'firstName',
    headerName: 'First name',
    width: 150,
    editable: true,
  },
  {
    field: 'lastName',
    headerName: 'Last name',
    width: 150,
    editable: true,
  },
  {
    field: 'age',
    headerName: 'Age',
    type: 'number',
    width: 110,
    editable: true,
  },
  {
    field: 'fullName',
    headerName: 'Full name',
    description: 'This column has a value getter and is not sortable.',
    sortable: false,
    width: 160,
    valueGetter: (params) =>
      `${params.row.firstName || ''} ${params.row.lastName || ''}`,
  },
];

const rows = [
  { id: 1, lastName: 'Snow', firstName: 'Jon', age: 35 },
  { id: 2, lastName: 'Lannister', firstName: 'Cersei', age: 42 },
  { id: 3, lastName: 'Lannister', firstName: 'Jaime', age: 45 },
  { id: 4, lastName: 'Stark', firstName: 'Arya', age: 16 },
  { id: 5, lastName: 'Targaryen', firstName: 'Daenerys', age: null },
  { id: 6, lastName: 'Melisandre', firstName: null, age: 150 },
  { id: 7, lastName: 'Clifford', firstName: 'Ferrara', age: 44 },
  { id: 8, lastName: 'Frances', firstName: 'Rossini', age: 36 },
  { id: 9, lastName: 'Roxie', firstName: 'Harvey', age: 65 },
];
  return (
    <>
      <Box m="30px 0px 50px 0px">
      {/* CONTACT INFO */}
      <Box>
      <Typography variant="h6" gutterBottom>
        Shipping address
      </Typography>
      <Grid container spacing={3} mt="0px">
        <Grid item xs={12} sm={6}>
          <Typography
    
            fullWidth
            variant="standard"
          > First Name: {addressUser.shipName} </Typography> 
        </Grid>
        <Grid item xs={12} sm={6}>
        <Typography
    
    fullWidth
    variant="standard"
  > Last Name </Typography> 
        </Grid>
        <Grid item xs={6}>
        <Typography
    
    fullWidth
    variant="standard"
  > Address Line 1: {addressUser.shipAddress} </Typography> 
        </Grid>
        
        <Grid item xs={12} sm={6}>
        <Typography
    
    fullWidth
    variant="standard"
  > City: {addressUser.city} </Typography> 
        </Grid>
        <Grid item xs={12} sm={6}>
        <Typography
    
    fullWidth
    variant="standard"
  > State/Provine/Region: {addressUser.state} </Typography> 
        </Grid>
        <Grid item xs={12} sm={6}>
        <Typography
    fullWidth
    variant="standard"
  > Zip/Post Code: {addressUser.zipCode} </Typography> 
        </Grid>
        <Grid item xs={12} sm={6}>
        <Typography
    fullWidth
    variant="standard"
  > Country </Typography> 
        </Grid>
       
      </Grid>
      </Box>
      <Box sx={{ height: '100%', width: '100%'   }}>
      <Typography mt="30px" variant="h6" gutterBottom>
        
       Product Order : 
      </Typography>
      <Grid container spacing={3} mt="0px">
      <Grid item xs={1} sm={1} height="400px">
        </Grid>
          <Grid item xs={11} sm={11} >
          {/* <DataGrid
        rows={rows}
        columns={columns}
        getRowHeight={() => '30px'}
        height="100%"
        
        // hideFooter={true}
        showCellRightBorder={true}
        showColumnRightBorder={true}
        sx={{
          boxShadow: 2,
          border: 1,
          borderColor: 'primary.light',
          '& .MuiDataGrid-cell:hover': {
            color: 'primary.main',
          },
        }}
        initialState={{
          // pagination: {
          //   paginationModel: {
          //     pageSize: 5,
          //   },
          // },
          aggregation: {
            model: {
              age: 'sum',
              
            },
          },
        }}
        // pageSizeOptions={[5]}
        // checkboxSelection
        disableRowSelectionOnClick
      /> */}

    <Box
      //display={isCartOpen ? "block" : "none"}
      backgroundColor="rgb  a(0, 0, 0, 0.4)"
     // position="fixed"
      //zIndex={10}
      width="100%"
      height="100%"
      left="0"
      top="0"
      overflow="auto"
    >
      <Box
        //position="fixed"
        right="0"
        bottom="0"
        width="100%"
        //height="100%"
        backgroundColor="white"
      >
        <Box padding="30px" overflow="auto" height="100%">

          {/* CART LIST */}
          <Box>
            {orders.map((item) => (
              <Box key={`${item.name}-${item.id}`}>
                <FlexBox p="15px 0">
                  <Box flex="1 1 40%">
                    <img
                      alt={item?.name}
                      width="123px"
                      height="164px"
                      src={`http://localhost:4000/images/${item.imageproduct}`}
                    />
                    
                  </Box>
                  <Box flex="1 1 30%">
                    <FlexBox mb="5px">
                      <Typography fontWeight="bold">
                        {item.name} 
                      </Typography>
                      
                    </FlexBox>
                    <Typography>{item.description}  </Typography>
                    
                  </Box>
                  <Box flex="1 1 30%">
                    <FlexBox mb="5px">
                      <Typography fontWeight="bold">
                        {item.realPrice} x  {item.quantity} = {item.realPrice*item.quantity}
                      </Typography>
                     
                    </FlexBox>
                    
                    
                  </Box>
                </FlexBox>
                <Divider />
              </Box>
            ))}
          </Box>

          {/* ACTIONS */}
          <Box m="20px 0">
            <FlexBox m="20px 0">
              <Typography fontWeight="bold" fontSize="20px"></Typography>
              <Typography fontWeight="bold" fontSize="20px">TOTAL:   ${totalPrice}</Typography>
            </FlexBox>
           
          </Box>
        </Box>
      </Box>
    </Box>
          </Grid>
        </Grid>
      
    </Box>
  
    </Box>
    </>
  );
}