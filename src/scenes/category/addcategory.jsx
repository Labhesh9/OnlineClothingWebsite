import { Box, Button, TextField } from "@mui/material";
import { Formik } from "formik";
import * as yup from "yup";
import useMediaQuery from "@mui/material/useMediaQuery";
import Header from "../../components/Header";
import axios from "axios";

const AddCatelogy = () => {
  const isNonMobile = useMediaQuery("(min-width:600px)");

  const handleFormSubmit = (values) => {
    console.log(values.name)
    const temp = {
      nameCategory: values.nameCategory,
      description: values.description
       };
    axios.post(`http://localhost:4000/api/categorys`,temp).then((response) => {
      //console.log(response.data);
     

  });
  }
  return (
    <Box m="20px">
      <Header title="CREATE CATEGORY" subtitle="Create a New catelogy" />

      <Formik
        onSubmit={handleFormSubmit}
        initialValues={initialValues}
        validationSchema={checkoutSchema}
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
                label="Name of Category"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.name}
                name="nameCategory"
                // error={!!touched.nameCategory && !!errors.nameCategory}
                // helperText={touched.nameCategory && errors.nameCategory}
                sx={{ gridColumn: "span 4" }}
              />
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
                sx={{ gridColumn: "span 4" }}
              />
            </Box>
            <Box display="flex" justifyContent="end" mt="20px">
              <Button type="submit" color="secondary" variant="contained">
                Create New Catogory
              </Button>
            </Box>
          </form>
        )}
      </Formik>
    </Box>
  );
};


const checkoutSchema = yup.object().shape({
  nameCategory: yup.string(),
  description: yup.string(),
});
const initialValues = {
  nameCategory: "",
  description:"",
};

export default AddCatelogy;
