import React from 'react';

import { Table } from 'react-bootstrap';

const JMoneyTable = props => {
  return (
    <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Item</th>
            <th>Price</th>
            <th>When</th>
          </tr>
        </thead>
        <tbody>

        {props.payments.map((item, index) => (
          <tr key={index}>

            <td>{index}</td>
            <td>{item.reason}</td>
            <td>{item.amount}</td>
            <td>{item.paymentDate}</td>
          </tr>
        ))}

        </tbody>
      </Table>
  );
};

export default JMoneyTable;
