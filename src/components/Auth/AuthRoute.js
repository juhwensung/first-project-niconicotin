import React from "react";
import { Route, Navigate } from "react-router-dom";
import Login from "../pages/UserPage/Login/Login";

export default function AuthRoute({ version, component: Component, ...rest }) {
  if (version === 1) {
    return (
      <Route
        {...rest}
        render={(props) =>
          Login() ? <Component {...props} /> : <Navigate to="/login" />
        }
      />
    );
  }
}
