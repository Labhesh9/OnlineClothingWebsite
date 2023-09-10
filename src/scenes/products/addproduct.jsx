import { Box, Button, TextField } from "@mui/material";
import { Formik } from "formik";
import * as yup from "yup";
import useMediaQuery from "@mui/material/useMediaQuery";
import Header from "../../components/Header";
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Alert from "@mui/material/Alert";
import Stack from "@mui/material/Stack";
import axios from "axios";
import { useState, useRef } from "react";
const AddProduct = () => {
  const formikRef = useRef();
  const isNonMobile = useMediaQuery("(min-width:600px)");
  const [agetype, setAgetype] = useState([]);
  const [category, setCategory] = useState([]);
  const [imageproduct, setImageproduct] = useState("");
  const [productId, setProductId] = useState(0);
  const [isCreated, setIsCreated] = useState(0);
   const [initialValues,setInitialValues] = useState({
          nameProduct: "",
          categoryId:4,
          agetypesId:1,
          description: "",
          imageproduct: "",
          originalPrice: 1,
   });
   const resetInitialValues = {
    nameProduct: "",
    categoryId:4,
    agetypesId:1,
    description: "",
    imageproduct: "",
    originalPrice: 1,
};
  
   const addProductDetail = (item) =>{

      axios.post(`http://localhost:4000/api/productDetail`,item).then((response) => {
          
               
      
                if (response.data.status === "success")
                {
                   setIsCreated(1);
                   setInitialValues({...initialValues,resetInitialValues})
                   formikRef.current.resetForm();
                  //  initialValues.agetypesId=0;
                  //  initialValues.categoryId=4;
                  //  initialValues.description="";
                  //  initialValues.originalPrice=0;
                   
                   
                }
                else
                {
                  setIsCreated(2);
                }

      }); 
   }


  const handleFormSubmit = (values) => {
    //debugger
     var product={
          name: values.nameProduct,
          contentHTML:"",
          contentMarkdown:"" ,
          statusId:"1", 
          categoryId:values.categoryId,
          agetypesId:values.agetypesId,
          view:1,
          madeby:"" ,
          material:"Cotton" , 
          brandId:1
     };
  
      var productDetail={
            productId:0,
            description:values.description,
            nameDetail:values.nameProduct ,
            imageproduct:"",
            originalPrice: values.originalPrice
          };
     
    
     axios.post(`http://localhost:4000/api/products`,product).then((response) => {
            setProductId(response.data.data.insertId);
            productDetail.productId = response.data.data.insertId;
            productDetail.imageproduct = imageproduct;
            addProductDetail(productDetail);
            console.log(productDetail);

          });
       
          
      

  };
  const onFileChange = (e) => {

    var formData = new FormData();
    formData.append('image', e.target.files[0]);
    axios.post(`http://localhost:4000/api/productImages/uploadfile`,formData).then((response) => {
            //productDetail.imageproduct = response.data.data;
            setImageproduct(response.data.data);
            //console.log(response.data.data);
          }); 
    //formData.append('image', profileImg);
    //profileImg.name = "abc.jpg";
    //console.log(newFile);
   // console.log(formData);
}
  axios.get(`http://localhost:4000/api/agetypes`).then((response) => {
    //console.log(response.data.data);
    setAgetype(response.data.data);
  //   orders = response.data.data;
  //   console.log("Rows orders:")
  //   console.log(orders); 
  //   for (let index = 0; index < orders.length; index++) {
  //     const item = orders[index];
  //     console.log("Row:")
  //     console.log(item); 
  //     rows.push(createData(item.id,item.codeOrder,item.createAt,item.name,item.originalPrice,item.quantity, item.imageproduct))
  //  }
  // console.log("Rows:")
  // console.log(rows); 
  });

  axios.get(`http://localhost:4000/api/categorys`).then((response) => {
    //console.log(response.data.data);
    setCategory(response.data.data);
  //   orders = response.data.data;
  //   console.log("Rows orders:")
  //   console.log(orders); 
  //   for (let index = 0; index < orders.length; index++) {
  //     const item = orders[index];
  //     console.log("Row:")
  //     console.log(item); 
  //     rows.push(createData(item.id,item.codeOrder,item.createAt,item.name,item.originalPrice,item.quantity, item.imageproduct))
  //  }
  // console.log("Rows:")
  // console.log(rows); 
  });
 
  return (
    <Box m="20px">
      <Header title="CREATE PRODUCTS" subtitle="Create a New Product" />
      <Stack mb="20px" sx={{ width: "100%" }} spacing={2}>
      { isCreated === 1 && (
                <Alert severity="success">The product was created</Alert>
              )}
      { isCreated === 2 && (
                <Alert severity="error">The product was not created</Alert>
              )}
      {/* <Alert severity="warning">This is a warning alert — check it out!</Alert>
      <Alert severity="info">This is an info alert — check it out!</Alert>
      <Alert severity="success">This is a success alert — check it out!</Alert> */}
      </Stack>
      <Formik
        //onSubmit={handleFormSubmit}
        onSubmit={handleFormSubmit}
        initialValues={initialValues}
        validationSchema={checkoutSchema}
        innerRef={(ref) => {
          formikRef.current = ref;
        }}
      >
        {({
          values,
          errors,
          touched,
          handleBlur,
          handleChange,
          handleSubmit,
        }) => (
          <form onSubmit={handleSubmit}>
            <Box
              display="grid"
              gap="30px"
              gridTemplateColumns="repeat(4, minmax(0, 1fr))"
              sx={{
                "& > div": { gridColumn: isNonMobile ? undefined : "span 4" },
              }}
            >
              
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="Name of Product"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.nameProduct}
                name="nameProduct"
              
                sx={{ gridColumn: "span 2" }}
              />
          
              <FormControl  sx={{ gridColumn: "span 1" }}>
              <InputLabel id="demo-simple-select-label">Type of Ages</InputLabel>
                <Select
                  fullWidth
                  variant="filled"
                  // labelId="demo-simple-select-helper-label"
                  //id="demo-simple-select-helper"
                  value={values.agetypesId}
                  label="Type of Ages"
                  name="agetypesId"
                  onBlur={handleBlur}
                  onChange={handleChange}
                 
                >
                  {/* <MenuItem value="">
                    <em>None</em>
                  </MenuItem> */}
                  {agetype.map((age) => (
            <MenuItem
              key={age.name + age.id}
              value={age.id}
              
            >
              {age.name}
            </MenuItem>
            ))}
                </Select>
                
              </FormControl>
              <FormControl  sx={{ gridColumn: "span 1" }}>
              <InputLabel id="demo-simple-select-label">Category</InputLabel>
                <Select
                  fullWidth
                  variant="filled"
                  // labelId="demo-simple-select-helper-label"
                  //id="demo-simple-select-helper"
                  value={values.categoryId}
                  label="Type of Catelogy"
                  name="categoryId"
                  onBlur={handleBlur}
                  onChange={handleChange}
                  error={!!touched.nameProduct && !!errors.nameProduct}
                  helperText={touched.nameProduct && errors.nameProduct}
                >
                  {/* <MenuItem value="">
                    <em>None</em>
                  </MenuItem> */}
                  {category.map((cat) => (
                    <MenuItem
                      key={cat.nameCategory + cat.id}
                      value={cat.id}
                    >
                      {cat.nameCategory}
                    </MenuItem>
            ))}
                </Select>
                
              </FormControl>
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="Description"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.description}
                name="description"
                // error={!!touched.description && !!errors.description}
                // helperText={touched.description && errors.description}
                sx={{ gridColumn: "span 2" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="file"
                label="Image Product"
                onBlur={handleBlur}
                onChange={onFileChange}
                value={values.imageproduct}
                name="imageproduct"
                
                sx={{ gridColumn: "span 2" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="Price of Product"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.originalPrice}
                name="originalPrice"
              
                sx={{ gridColumn: "span 4" }}
              />
              
            </Box>
            <Box display="flex" justifyContent="end" mt="20px">
              <Button type="submit" color="secondary" variant="contained">
                Create a new product
              </Button>
            </Box>
          </form>
        )}
      </Formik>
    </Box>
  );
};

const phoneRegExp =
  /^((\+[1-9]{1,4}[ -]?)|(\([0-9]{2,3}\)[ -]?)|([0-9]{2,4})[ -]?)*?[0-9]{3,4}[ -]?[0-9]{3,4}$/;

const checkoutSchema = yup.object().shape({
  categoryId: yup.number(),
  agetypesId: yup.number(),
  nameProduct: yup.string().required("required"),
  description: yup.string().required("required"),
  imageproduct: yup.string(),
  originalPrice: yup.number(),
    
});


export default AddProduct;
