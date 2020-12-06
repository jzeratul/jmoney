import React from 'react'
import { Button } from 'react-bootstrap'

const JMoneyJarButtons = props => {

  const onClickHandler = () => {
    props.jarSelectHandler(props.jars)
  };

  return (
    <div>
        {props.jars.map((jar, index) => (

          <Button
            variant={props.jar.variant}
            name={props.jar.name}
            onClick={onClickHandler}
          >
            {props.jar.name} € {props.jar.money}
          </Button>
        ))}
    </div>
  )
}

export default JMoneyJarButtons