import * as React from 'react';
import { useSelector, useDispatch } from "react-redux";
import PropTypes from 'prop-types';
import Box from '@mui/material/Box';
import Collapse from '@mui/material/Collapse';
import IconButton from '@mui/material/IconButton';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import  { useState, useEffect } from "react";

import axios from "axios";

var rows=[];
function createData(id, codeOrder, createAt, name, originalPrice, quantity,imageproduct) {
  return {
    id,
    codeOrder,
    createAt,
    imageproduct,
    history: [
      {
        name,
        originalPrice,
        quantity,
      },
    ],
  };
}

function Row(props) {
  const { row, totalPrice } = props;

  const [open, setOpen] = React.useState(false);

  return (
    <React.Fragment>
      <TableRow sx={{ '& > *': { borderBottom: 'unset' } }}>
        <TableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={() => setOpen(!open)}
          >
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell component="th" scope="row">
          {row.codeOrder}
        </TableCell>
        <TableCell align="right">{row.createAt}</TableCell>
        <TableCell align="right">{totalPrice}</TableCell>
       
        {/* <TableCell align="right">{row.carbs}</TableCell>
        <TableCell align="right">{row.protein}</TableCell> */}
      </TableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1 }}>
              <Typography variant="h6" gutterBottom component="div">
                Products: 
              </Typography>
              <Table size="small" aria-label="purchases">
                <TableHead>
                  <TableRow>
                    <TableCell>Name of Product</TableCell>
                    <TableCell>Price</TableCell>
                    <TableCell align="right">Quantiy</TableCell>
                    <TableCell align="right">Total price ($)</TableCell>
                  </TableRow>
                </TableHead>
                
                <TableBody>
                  {row.history.map((historyRow) => (
                    
                    <TableRow key={historyRow.createAt}>
                      <TableCell> <img
                      alt={row?.name}
                      width="123px"
                      height="164px"
                      src={`http://localhost:4000/images/${row.imageproduct}`}
                    /></TableCell>
                      <TableCell component="th" scope="row">
                        {historyRow.name}
                      </TableCell>
                      <TableCell>{historyRow.originalPrice}</TableCell>
                      <TableCell align="right">{historyRow.quantity}</TableCell>
                      <TableCell align="right">
                        {Math.round(historyRow.quantity * historyRow.originalPrice)}
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
}
//
//
//

//createData(id, codeOrder, createAt, name, originalPrice, quantity)
Row.propTypes = {
  row: PropTypes.shape({
    id: PropTypes.number.isRequired,
    codeOrder: PropTypes.string.isRequired,
    createAt: PropTypes.string.isRequired,
    imageproduct: PropTypes.string.isRequired,
    history: PropTypes.arrayOf(
      PropTypes.shape({
        name: PropTypes.string.isRequired,
        originalPrice: PropTypes.number.isRequired,
        quantity: PropTypes.number.isRequired,
      }),
    
    ).isRequired,
    // name: PropTypes.string.isRequired,
    // price: PropTypes.number.isRequired,
    // protein: PropTypes.number.isRequired,
  }).isRequired,
};



export default function OrderList() {
    //const rows = props.rows;
    var  orders = [];
    var  userId  = sessionStorage.getItem("uid");
    const dispatch = useDispatch();
    //const orders = useSelector((state) => state.cart.orders);
    //var [rows, setRows] = useState([]);
   // const totalPrice = orders.reduce((total, item) => {
    //return total + item.quantity * item.realPrice;
    //    }, 0);

        

        //const addShopCartToOrder = (item) =>
  //  const  loadOrders = () => {
  //       debugger
  //        var  userId  = sessionStorage.getItem("uid");
  //       // const items = await fetch(
  //       //     `http://localhost:4000/api/orderProductDetails/userid/${userId}`,
  //       //     { method: "GET" }
  //       // );
  //       // const itemsJson = await items.json();
  //       // rows = itemsJson.data;
       

  //   var helper = new XMLHttpRequest();
  //   helper.onreadystatechange = () => {
  //   if (helper.readyState === 4 && helper.status === 200) {
  //     var result = JSON.parse(helper.responseText);
  //     if (result.status === "success") {
  //         dispatch(setOrders(result.data))
  //         //rows = result.data;
  //         console.log("Rows:")
  //         console.log(rows);  
  //       }
  //     }
  //     const url = `http://localhost:4000/api/orderProductDetails/userid/${userId}`;
  //     helper.open("GET", url);
  //     helper.setRequestHeader("content-type", "application/json");
  //     helper.send();
  //   }
  // };
  
   function getOrders() {
     
    //createData(id, codeOrder, createAt, name, originalPrice, quantity)
    axios.get(`http://localhost:4000/api/orderProductDetails/userid/${userId}`).then((response) => {
      //console.log(response.data.data);
      //setOrders(response.data.data);
      orders = response.data.data;
      console.log("Rows orders:")
      console.log(orders); 
      for (let index = 0; index < orders.length; index++) {
        const item = orders[index];
        console.log("Row:")
        console.log(item); 
        rows.push(createData(item.id,item.codeOrder,item.createAt,item.name,item.originalPrice,item.quantity, item.imageproduct))
     }
    console.log("Rows:")
    console.log(rows); 
    });
    //dispatch(setItems(itemsJson.data));
  }


  useEffect(() => {
    //loadOrders();
    //debugger;
    //getOrders();
    axios.get(`http://localhost:4000/api/orderProductDetails/userid/${userId}`).then((response) => {
      //console.log(response.data.data);
      //setOrders(response.data.data);
      orders = response.data.data;
      console.log("Rows orders:")
      console.log(orders); 
      rows=[];
      for (let index = 0; index < orders.length; index++) {
        const item = orders[index];
        console.log("Row:")
        console.log(item); 
        rows.push(createData(item.id,item.codeOrder,item.createAt,item.name,item.originalPrice,item.quantity,item.imageproduct))
     }
    console.log("Rows:")
    console.log(rows); 
    });

  },[]);
       
  return (
    <Box width="80%" m="100px auto">
<Typography  variant="h1">LIST OF YOUR ORDERS</Typography>
<TableContainer component={Paper}>
      <Table aria-label="collapsible table">
        <TableHead>
          <TableRow>
            <TableCell />
            <TableCell>Code order</TableCell>
            <TableCell align="right">Day Order</TableCell>
            <TableCell align="right">Status</TableCell>
            <TableCell align="right">Total</TableCell>
            <TableCell align="right">Protein&nbsp;(g)</TableCell>
          </TableRow>
        </TableHead>
        
        <TableBody>
          {rows.map((row) => (
            
            <Row key={row.name + row.id} row={row}  />
          ))}
        </TableBody>
      </Table>
    </TableContainer>

    </Box>
   
  );
}