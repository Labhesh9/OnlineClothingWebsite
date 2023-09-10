import * as React from 'react';
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from 'react-router-dom';
import { useDispatch, useSelector } from "react-redux";
import {  loadItemsCart } from "../../state";
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { addCartToDataBase, loadCartFromDataBase } from '../../components/funtion/Ulit';

// function Copyright(props) {
//   return (
//     <Typography variant="body2" color="text.secondary" align="center" {...props}>
//       {'Copyright © '}
//       <Link color="inherit" href="https://mui.com/">
//         Your Website
//       </Link>{' '}
//       {new Date().getFullYear()}
//       {'.'}
//     </Typography>
//   );
// }


// TODO remove, this demo shouldn't need to reset the theme.

const defaultTheme = createTheme();

export default function Login(props) {
 // const { props } = this.props
  var [user,setUser] =  useState({email: "", password: ""});
  //var [carts, setCarts] =  useState([]);
  var navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();
  const handleSubmit = (event) => {
  
    event.preventDefault();
    //const data = new FormData(event.currentTarget);
    // const e = data.get('email');
    // const p = data.get('password');
    
    // console.log({
    //   email: data.get('email'),
    //   password: data.get('password'),
    // });
    console.log(user);
    DoLogin()
    
   //DoLogin();
  };

  var TextChanged = function(args)
    {
        var copyOfUser = {...user};
        copyOfUser[args.target.name] = args.target.value;
        setUser(copyOfUser);
        //console.log(user);
    }

    // function getCarts() {
    //   setCarts(JSON.parse(sessionStorage.getItem("cart"))) ;
    //   console.log("carts");
    //   //console.log(carts);
    //   if (carts !== null){
    //      dispatch(loadItemsCart(carts));
    //     //  carts.map((m)=> console.log(m)
    //     //  )
    //   }
     
    // }
    
    // useEffect(() => {
    //   getCarts();
    // }, [carts]); // eslint-disable-line react-hooks/exhaustive-deps

  var DoLogin = function()
    {

       //var encoded = window.btoa(JSON.stringify(user));
     //  var CredentialsToPass = {credentials: JSON.stringify(user)} ;
     //  var CredentialsToPassInString = JSON.stringify(CredentialsToPass);
      console.log(user);
       var helper = new XMLHttpRequest();
       helper.onreadystatechange = ()=>{
        if(helper.readyState === 4 && helper.status === 200)
        {
            //debugger;
            var result = JSON.parse(helper.responseText);
            if(result.status === "success")
            {
                sessionStorage.setItem("isUserLoggedIn","true");
                sessionStorage.setItem("username",user.username);
                sessionStorage.setItem("role",result.data.data.roleid);
                console.log(result.data.data.id);
                sessionStorage.setItem("uid",result.data.data.id);

                var carts = JSON.parse(sessionStorage.getItem("cart"));
                console.log("carts 1:");

                if (carts !== null){
                    carts.map((m)=> addCartToDataBase(m)
                    )
                }
                loadCartFromDataBase();
                //getCarts();
               
                
                if(location.pathname!==null && location.pathname!=="/login" && location.pathname!=="/checkout")
                {
                  //navigate.push(props.path) //this is like take user to DB/profile
                  navigate(location.pathname)
                  //return <Navigate to="/home" replace={true} />;
                  //navigate(props.path)
                }
                else 
                {
                    navigate("/")
                    //return <Navigate to="/home" replace={true} />;
                    //history.push("/home");  //this will execute when user asks for /login directly..
                }
                
            }
            else
            {
                setUser({username: "", password: ""});
                //ShowMessage("Credentials are invalid!");
            }
        }
       };
       helper.open("POST", "http://localhost:4000/api/users/login");
       helper.setRequestHeader("content-type", "application/json");
      // debugger;
       helper.send(JSON.stringify(user));
    }
  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              autoFocus
              onChange={TextChanged}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
              onChange={TextChanged}
            />
            <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            />
            <Button
              type="submit"
              fullWidth
              onClick={() => console.log("Hello")}
            //  onClick={()=>{DoLogin()}}
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item xs>
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <Link href="/signup" variant="body2">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        {/* <Copyright sx={{ mt: 8, mb: 4 }} /> */}
      </Container>
    </ThemeProvider>
  );
}