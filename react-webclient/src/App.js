
 import React from 'react';
 import { BrowserRouter, Switch, NavLink, Redirect, Route } from 'react-router-dom';

 import Login from './pages/Login';
 import Dashboard from './pages/Dashboard';

 function App() {
   return (
     <div className="App">
       <BrowserRouter>
         <div>
           <div className="header">
             <NavLink activeClassName="active" to="/login">Login</NavLink>
             <NavLink activeClassName="active" to="/dashboard">Dashboard</NavLink>
           </div>
           <div className="content">
             <Switch>
               <Route path="/login" component={Login} />
               <Route path="/dashboard" component={Dashboard} />
               <Redirect to="/login" />
             </Switch>
           </div>
         </div>
       </BrowserRouter>
     </div>
   );
 }

 export default App;