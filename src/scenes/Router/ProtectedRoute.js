import { Route } from "react-router-dom";
import Login from "./Login";

function ProtectedRoute(props)
{
    debugger;
    var isUserLoggedIn = sessionStorage.getItem('isUserLoggedIn');
    var loggedInUserName = sessionStorage.getItem('username');

    if(isUserLoggedIn!=null && isUserLoggedIn == 'true')
    {
        props.setUser(loggedInUserName);
        //if loggedin then return expected component
        return <Route exact path={props.path} 
        component={props.component} />;
    }
    else
    {
       props.setUser("Guest");
        //If not logged In return Login Component
       return <Login path={props.path}></Login>
    }
}

export default ProtectedRoute;