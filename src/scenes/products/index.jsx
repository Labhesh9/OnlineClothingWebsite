import { Avatar, Box } from "@mui/material";
import { DataGrid, GridToolbar } from "@mui/x-data-grid";
import { tokens } from "../../theme";
//import { mockDataContacts } from "../../data/mockData";
import Header from "../../components/Header";
import { useTheme } from "@mui/material";
import AddProduct from "./addproduct";
import axios from "axios";
import { useEffect, useState } from "react";

const Products = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  var [dataProduct,setDataProduct] = useState([]);
  const columns = [
    { field: "id", headerName: "ID", flex: 0.1 },
    { field: "imageproduct",
      
      renderCell: (params) => {
          console.log("params");
          console.log(params);
          return (
            <>
             
              <img
                      alt=""
                      width="123px"
                      height="164px"
                      src={`http://localhost:4000/images/${params.value}`}
                    />
            </>
          );
      }
  },
    {
      field: "name",
      headerName: "Name of product",
      flex: 1,
      cellClassName: "name-column--cell",
    },
    {
      field: "nameCategory",
      headerName: "Categoty",
      //type: "number",
      headerAlign: "left",
      align: "left",
    },
    {
      field: "nameAgetype",
      headerName: "Age Type",
      flex: 1,
    },
    // {
    //   field: "email",
    //   headerName: "Email",
    //   flex: 1,
    // },
    // {
    //   field: "address",
    //   headerName: "Address",
    //   flex: 1,
    // },
    // {
    //   field: "city",
    //   headerName: "City",
    //   flex: 1,
    // },
    // {
    //   field: "zipCode",
    //   headerName: "Zip Code",
    //   flex: 1,
    // },
  ];

  useEffect(()=>{
    axios.get(`http://localhost:4000/api/products/getAllProductWithPrice`).then((response) => {
      console.log(response.data);
      setDataProduct(response.data.data);
      //dataAgeType = response.data.data;
  
  });
  },[])
  return (
    <Box m="20px">
      <Box>
        <AddProduct />
      </Box>
      <Header
        title="PRODUCTS"
        subtitle="List of Products:"
      />
      <Box
        m="40px 0 0 0"
        height="75vh"
        sx={{
          "& .MuiDataGrid-root": {
            border: "none",
          },
          "& .MuiDataGrid-cell": {
            borderBottom: "none",
          },
          "& .name-column--cell": {
            color: colors.greenAccent[300],
          },
          "& .MuiDataGrid-columnHeaders": {
            backgroundColor: colors.blueAccent[700],
            borderBottom: "none",
          },
          "& .MuiDataGrid-virtualScroller": {
            backgroundColor: colors.primary[400],
          },
          "& .MuiDataGrid-footerContainer": {
            borderTop: "none",
            backgroundColor: colors.blueAccent[700],
          },
          "& .MuiCheckbox-root": {
            color: `${colors.greenAccent[200]} !important`,
          },
          "& .MuiDataGrid-toolbarContainer .MuiButton-text": {
            color: `${colors.grey[100]} !important`,
          },
        }}
      >
        <DataGrid
          rows={dataProduct}
          columns={columns}
          components={{ Toolbar: GridToolbar }}
          rowHeight="164px"
        />
      </Box>
    </Box>
  );
};

export default Products;
