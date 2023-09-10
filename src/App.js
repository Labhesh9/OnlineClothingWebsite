import { useEffect } from "react";
import React, { useState } from "react";
import {
  BrowserRouter,
  Routes,
  Route,
  useLocation,
  createBrowserRouter,
  createRoutesFromElements,
  RouterProvider,
  Navigate,
} from "react-router-dom";
import Home from "./scenes/home/Home";
import Navbar from "./scenes/global/Navbar";
import Footer from "./scenes/global/Footer";
import ItemDetails from "./scenes/itemDetails/ItemDetails";
import CartMenu from "./scenes/global/CartMenu";
import Checkout from "./scenes/checkout/Checkout";
import Confirmation from "./scenes/checkout/Confirmation";
import Protected from "./scenes/Router/Protected";
import Login from "./scenes/home/Login";
import SignUp from "./scenes/home/SignUp";
import Administrator from "./Admin";
import { Outlet } from "react-router-dom";
import Topbar from "./scenes/global/Topbar";
import Sidebar from "./scenes/global/Sidebar";
import Dashboard from "./scenes/dashboard";
import Team from "./scenes/team";
import Invoices from "./scenes/invoices";
import Contacts from "./scenes/contacts";
import Bar from "./scenes/bar";
import Form from "./scenes/form";
import Line from "./scenes/line";
import Pie from "./scenes/pie";
import FAQ from "./scenes/faq";
import Geography from "./scenes/geography";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { ColorModeContext, useMode } from "./theme";
import Calendar from "./scenes/calendar/calendar";
import OrderList from "./scenes/orders/orderslist";
import Products from "./scenes/products";
import AgesType from "./scenes/agestype";
import Category from "./scenes/category";
const ScrollToTop = () => {
  const { pathname } = useLocation();

  //const [isSignedIn, setIsSignedIn] = useState(true);

  return null;
};

function App() {
  const [initialValues, setInitialValues] = useState({
    billingAddress: {
      id: 0,
      firstName: "",
      lastName: "",
      country: "",
      street1: "sdadsasdas",
      street2: "asdasd",
      city: "",
      state: "",
      zipCode: "",
    },

    email: "",
    phoneNumber: "",
  });
  const [theme, colorMode] = useMode();
  const [isSidebar, setIsSidebar] = useState(true);
  useEffect(() => {
    loadAddressUserByUserId();
  }, []);
  const loadAddressUserByUserId = async () => {
    var userId = sessionStorage.getItem("uid");
    var helper = new XMLHttpRequest();
    helper.onreadystatechange = () => {
      if (helper.readyState === 4 && helper.status === 200) {
        var result = JSON.parse(helper.responseText);
        if (result.status === "success") {
          console.log("Load address");
          console.log(result.data);
          setInitialValues({
            billingAddress: {
              id: result.data.id,
              firstName: result.data.shipName,
              lastName: "ASAsA",
              country: result.data.country,
              street1: result.data.street1,
              street2: result.data.street2,
              city: result.data.city,
              state: result.data.state,
              zipCode: result.data.zipCode,
            },

            email: result.data.shipEmail,
            phoneNumber: result.data.shipPhoneNumber,
          });
          //billingAddress.country = result.data.country;
          console.log(initialValues.billingAddress);
        }
      }
    };
    const url = `http://localhost:4000/api/addressUsers/${userId}`;
    console.log(url);
    helper.open("GET", url);
    helper.setRequestHeader("content-type", "application/json");
    // debugger;
    helper.send();
  };

  const ClientLayout = () => (
    <div className="app">
      <Navbar />
      <ScrollToTop />
      <Outlet />
      <CartMenu />
      <Footer />
    </div>
  );
  const AdminLayout = () => (
    <div>
      <ColorModeContext.Provider value={colorMode}>
        <ThemeProvider theme={theme}>
          <CssBaseline />
          <div className="app1">
            <Sidebar isSidebar={isSidebar} />
            <main className="content">
              <Topbar setIsSidebar={setIsSidebar} />
              <Outlet />
            </main>
          </div>
        </ThemeProvider>
      </ColorModeContext.Provider>
    </div>
  );

  const RoleAccess = ({ roles = [], children }) => {
    var userId = sessionStorage.getItem("uid");
    var role = "user";
    var helper = new XMLHttpRequest();
    //debugger;
    helper.onreadystatechange = () => {
      if (helper.readyState === 4 && helper.status === 200) {
        var result = JSON.parse(helper.responseText);
        if (result.status === "success") {
          role = result.data.roleid;
        }
      }
      const url = `http://localhost:4000/api/users/${userId}`;
      console.log(url);
      helper.open("GET", url);
      helper.send();
    };
    console.log("roles");
    console.log(roles);
    console.log("role");
    console.log(role);
    return !roles.length || roles.includes(role) ? (
      children
    ) : (
      <Navigate to="/" replace />
    );
  };
  // const router = createBrowserRouter(
  //   createRoutesFromElements([
  //     <Route exact path="/" element={ClientLayout}>
  //       <Route index element={<Home />} />
  //       <Route exact path="/login" element={<Login />} />
  //       <Route exact path="/signup" element={<SignUp />} />
  //       <Route path="/item/:itemId" element={<ItemDetails />} />
  //       <Route path="/confirm" element={<Confirmation />} />

  //       {/* <Route path="checkout" element={<Checkout />} /> */}
  //       <Route
  //         path="checkout"
  //         element={
  //           <Protected isSignedIn={true}>
  //             <Checkout initialValues={initialValues} />
  //           </Protected>
  //         }
  //       />

  //       <Route path="/checkout/success" element={<Confirmation />} />
  //     </Route>,
  //     <Route exact path="/admin" element={AdminLayout}>
  //       <Route index element={<Dashboard />} />
  //       <Route path="/team" element={<Team />} />
  //       <Route path="/contacts" element={<Contacts />} />
  //       <Route path="/invoices" element={<Invoices />} />
  //       <Route path="/form" element={<Form />} />
  //       <Route path="/bar" element={<Bar />} />
  //       <Route path="/pie" element={<Pie />} />
  //       <Route path="/line" element={<Line />} />
  //       <Route path="/faq" element={<FAQ />} />
  //       <Route path="/calendar" element={<Calendar />} />
  //       <Route path="/geography" element={<Geography />} />
  //     </Route>,
  //   ])
  // );

  const BrowserRoutes = createBrowserRouter([
    {
      element: <ClientLayout />,
      children: [
        {
          path: "",
          element: <Home />,
        },
        {
          path: "login",
          element: <Login />,
        },
        {
          path: "signup",
          element: <SignUp />,
        },
        {
          path: "item/:itemId",
          element: <ItemDetails />,
        },
        {
          path: "confirm",
          element: <Confirmation />,
        },
        {
          path: "orderlist",
          element: <OrderList />,
        },
        {
          path: "checkout",
          element: (
            <Protected isSignedIn={true}>
              {" "}
              <Checkout initialValues={initialValues} />{" "}
            </Protected>
          ),
        },
        {
          path: "checkout/success",
          element: <Confirmation />,
        },
      ],
    },
    {
      path: "/admin",
      element: <AdminLayout />,
      children: [
        {
          path: "",
          element: (
            <Protected roles={["admin"]}>
              <Dashboard />
            </Protected>
          ),
        },
        {
          path: "team",
          element: (
            <Protected roles={["admin"]}>
              <Team />
            </Protected>
          ),
        },
        {
          path: "contacts",
          element: (
            <Protected roles={["admin"]}>
              <Contacts />
            </Protected>
          ),
        },
        {
          path: "agestype",
          element: (
            <Protected roles={["admin"]}>
              <AgesType />
            </Protected>
          ),
        },
        {
          path: "category",
          element: (
            <Protected roles={["admin"]}>
              <Category />
            </Protected>
          ),
        },
        {
          path: "products",
          element: (
            <Protected roles={["admin"]}>
              <Products />
            </Protected>
          ),
        },
        {
          path: "invoices",
          element: (
            <Protected roles={["admin"]}>
              <Invoices />
            </Protected>
          ),
        },
        {
          path: "form",
          element: (
            <Protected roles={["admin"]}>
              <Form />
            </Protected>
          ),
        },
        {
          path: "bar",
          element: (
            <Protected roles={["admin"]}>
              <Bar />
            </Protected>
          ),
        },
        {
          path: "pie",
          element: (
            <Protected roles={["admin"]}>
              <Pie />
            </Protected>
          ),
        },
        {
          path: "line",
          element: (
            <Protected roles={["admin"]}>
              <Line />
            </Protected>
          ),
        },
        {
          path: "faq",
          element: (
            <Protected roles={["admin"]}>
              <FAQ />
            </Protected>
          ),
        },
        {
          path: "calendar",
          element: (
            <Protected roles={["admin"]}>
              <Calendar />
            </Protected>
          ),
        },
        {
          path: "geography",
          element: (
            <Protected roles={["admin"]}>
              <Geography />
            </Protected>
          ),
        },
      ],
    },
  ]);

  return (
    // <div className="app">
    //   <BrowserRouter>
    //     <Navbar />
    //     <ScrollToTop />
    //     <Routes>
    //       <Route exact path="/" element={<Home />} />
    //       <Route exact path="/login" element={<Login />} />
    //       <Route exact path="/signup" element={<SignUp />} />
    //       <Route path="/item/:itemId" element={<ItemDetails />} />
    //       <Route path="/confirm" element={<Confirmation />} />
    //       <Route path="/admin" element={<Administrator />} />
    //       {/* <Route path="checkout" element={<Checkout />} /> */}
    //       <Route
    //         path="checkout"
    //         element={
    //           <Protected isSignedIn={true}>
    //             <Checkout initialValues={initialValues} />
    //           </Protected>
    //         }
    //       />

    //       <Route path="/checkout/success" element={<Confirmation />} />
    //     </Routes>
    //     <CartMenu />
    //     <Footer />
    //   </BrowserRouter>
    // </div>

    <RouterProvider router={BrowserRoutes} />
  );
}

export default App;
