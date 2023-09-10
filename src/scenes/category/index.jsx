import { Box } from "@mui/material";
import { DataGrid, GridToolbar } from "@mui/x-data-grid";
import { tokens } from "../../theme";
//import { mockDataContacts } from "../../data/mockData";
import Header from "../../components/Header";
import { useTheme } from "@mui/material";
import axios from "axios";
import AddCatelogy from "./addcategory";
import { useEffect, useState } from "react";

const Category = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  var [dataCategory,setDataCategory] = useState([]);
  const columns = [
    { field: "id", headerName: "ID", flex: 0.1 },
    // { field: "registrarId", headerName: "nameCategory" },
    {
      field: "nameCategory",
      headerName: "Name of Category",
      flex: 0.5,
      cellClassName: "name-column--cell",
    },
    {
      field: "description",
      headerName: "Description",
     // type: "number",
     flex:1,
      headerAlign: "left",
      align: "left",
    },
    // {
    //   field: "phone",
    //   headerName: "Phone Number",
    //   flex: 1,
    // },
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
    axios.get(`http://localhost:4000/api/categorys`).then((response) => {
      console.log(response.data);
      setDataCategory(response.data.data);
      //dataAgeType = response.data.data;
  
  });
  },[])
  return (
    <Box m="20px">
      <Box>
        <AddCatelogy />
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
          rows={dataCategory}
          columns={columns}
          components={{ Toolbar: GridToolbar }}
        />
      </Box>
    </Box>
  );
};

export default Category;
