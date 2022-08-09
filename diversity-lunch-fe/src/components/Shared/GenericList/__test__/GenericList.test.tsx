import { render } from '@testing-library/react';
import { GenericList, GenericListItemComponentProp } from '../GenericList';
import { FC } from 'react';

interface SomeDataType {id: number, name:string}

describe('Profile Overview', () => {
  let someData: SomeDataType[];
  let container: HTMLElement;
  let itemComponent: FC<GenericListItemComponentProp<SomeDataType>>;
  let getKeyFunction: (_:SomeDataType) => number;

  beforeEach(() => {
    someData = [{id:0, name: "klaus"}, {id:42, name: "peter"}];
    itemComponent = ({item})=> <p>{item.name}</p>;
    getKeyFunction = (item) => item.id;
    ({ container } = render(<GenericList data={someData} itemComponent={itemComponent} getKeyFunction={getKeyFunction}/>));
  });

  it('should render component without crashing', () => {
    expect(container.firstChild).toBeInTheDocument();
  });

  it('should have as many children as data elements are given', () => {
    expect(container.firstChild!.childNodes.length).toEqual(someData.length);
  });

  it('should render data elements according to the given itemComponent', ()=>{
    for(let i = 0; i < someData.length; ++i) {
      expect(container.firstElementChild!.querySelectorAll('p')
        .item(i).innerHTML)
        .toEqual(someData[i].name);
    }
  });
});
