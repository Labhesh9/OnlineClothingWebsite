import { useSelector,useDispatch } from "react-redux";
import {  removeAllFromCart } from "../../state";
import { Box, Button, Stepper, Step, StepLabel } from "@mui/material";
import { Formik } from "formik";
import { useState, useEffect } from "react";

import * as yup from "yup";
import { shades } from "../../theme";
import Payment from "./Payment";
import Shipping from "./Shipping";

import Confirmation from "./Confirmation";
import { v4 as uuidv4 } from 'uuid';

import { addOrderToDataBase } from "../../components/funtion/Ulit";


const Checkout = (props) => {
  const [activeStep, setActiveStep] = useState(0);
  const [orderId, setOrderId] = useState(0);
  const cart = useSelector((state) => state.cart.cart);
  const isFirstStep = activeStep === 0;
  const isSecondStep = activeStep === 1;
  const isOrderStep = activeStep === 2;
  const [initialValues,setInitialValues] = useState(props.initialValues);
  const dispatch = useDispatch();

  var userId = sessionStorage.getItem("uid");
  const orders = {
    userId:userId ,
    statusId:"1",
    voucherId:"1" ,
    addressUserId: initialValues.billingAddress.id, 
    note:"",
    isPaymentOnline:1,
    codeOrder: uuidv4()
  }

  useEffect(() => {
   // loadAddressUserByUserId(userId);
  },[initialValues]); // eslint-disable-line react-hooks/exhaustive-deps
  
  
  
  const handleFormSubmit = async (values, actions) => {
    setActiveStep(activeStep + 1);

    if (isSecondStep) {
      makePayment();
    }
    actions.setTouched({});
  };


  // function addOrderToDataBase (item) {
  //   item = { ...item, userId };
  //   console.log("item add to products:");
  //   console.log(item);
  //   //console.log(item);
  //   //var isUserLoggedIn = sessionStorage.getItem("isUserLoggedIn");
  //   debugger;
  //   //if (isUserLoggedIn != null && isUserLoggedIn === "true") {
  //   var helper = new XMLHttpRequest();
  //   helper.onreadystatechange = () => {
  //     if (helper.readyState === 4 && helper.status === 200) {
  //       var result = JSON.parse(helper.responseText);
  //       if (result.status === "success") {
  //         setOrderId(result.data.insertId);
  //         console.log("item add to products:");
  //         console.log(orderId);
  //         item = { ...item, orderId };
  //         console.log("item order: ")
  //         console.log(item);
  //         addShopCartToOrder(item);
  //       }
  //     }
  //   };
  //   helper.open("POST", "http://localhost:4000/api/orderProducts");
  //   helper.setRequestHeader("content-type", "application/json");
  //   // debugger;
  //   helper.send(JSON.stringify(item));
  //   //}
  // };



  async function makePayment() {
    addOrderToDataBase(orders);
    // console.log("-----------");
    // console.log(orders);
    // console.log("-----------");
    dispatch(removeAllFromCart({}))
  }

  return (
    <Box width="80%" m="100px auto">
      <Stepper activeStep={activeStep} sx={{ m: "20px 0" }}>
        <Step>
          <StepLabel>Billing</StepLabel>
        </Step>
        <Step>
          <StepLabel>Payment</StepLabel>
        </Step>
      </Stepper>
      <Box>
        <Formik
          onSubmit={handleFormSubmit}
          initialValues={initialValues}
          validationSchema={checkoutSchema[activeStep]}
        >
          {({
            values,
            errors,
            touched,
            handleBlur,
            handleChange,
            handleSubmit,
            setFieldValue,
          }) => (
            <form onSubmit={handleSubmit}>
              {isFirstStep && (
                <Shipping
                  values={values}
                  errors={errors}
                  touched={touched}
                  handleBlur={handleBlur}
                  handleChange={handleChange}
                  setFieldValue={setFieldValue}
                />
              )}
              {isSecondStep && (
                <Payment
                  values={values}
                  errors={errors}
                  touched={touched}
                  handleBlur={handleBlur}
                  handleChange={handleChange}
                  setFieldValue={setFieldValue}
                />
              )}
              {isOrderStep && (
                <Confirmation orderId={orderId }  addressUserId = {orders.addressUserId}  />
              )}
              { !isOrderStep &&
              <Box display="flex" justifyContent="space-between" gap="50px">
                {!isFirstStep && (
                  <Button
                    fullWidth
                    color="primary"
                    variant="contained"
                    sx={{
                      backgroundColor: shades.primary[200],
                      boxShadow: "none",
                      color: "white",
                      borderRadius: 0,
                      padding: "15px 40px",
                    }}
                    onClick={() => setActiveStep(activeStep - 1)}
                  >
                    Back
                  </Button>
                )}
                <Button
                  fullWidth
                  type="submit"
                  color="primary"
                  variant="contained"
                  onClick={() => 
                    { if( !isOrderStep  ) {console.log("Payment click"); } }
                        
                      
                  }
                  sx={{
                    backgroundColor: shades.primary[400],
                    boxShadow: "none",
                    color: "white",
                    borderRadius: 0,
                    padding: "15px 40px",
                  }}
                >
                  {!isSecondStep ? "Next" : "Place Order"}
                </Button>
              </Box>
              }
            </form>
          )}
        </Formik>
      </Box>
    </Box>
  );
};



const checkoutSchema = [
  yup.object().shape({
    billingAddress: yup.object().shape({
     // firstName: yup.string().required("required"),
      firstName: yup.string(),
      lastName: yup.string(),
      country: yup.string(),
      street1: yup.string(),
      street2: yup.string(),
      city: yup.string(),
      state: yup.string(),
      zipCode: yup.string(),
    }),
   
  }),
  yup.object().shape({
    email: yup.string().required("required"),
    phoneNumber: yup.string().required("required"),
  }),
];

export default Checkout;
