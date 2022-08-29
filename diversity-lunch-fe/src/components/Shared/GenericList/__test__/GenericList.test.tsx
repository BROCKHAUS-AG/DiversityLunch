import { render } from '@testing-library/react';
import { FC } from 'react';
import { GenericList, GenericListItemComponentProp } from '../GenericList';

interface SomeDataType {id: number, name:string}

const itemComponent: FC<GenericListItemComponentProp<SomeDataType>> = ({ item }) => <p>{item.name}</p>;

describe('Profile Overview', () => {
  let someData: SomeDataType[];
  let container: HTMLElement;
  let getKeyFunction: (_:SomeDataType) => number;

  beforeEach(() => {
    someData = [{ id: 0, name: 'klaus' }, { id: 42, name: 'peter' }];
    getKeyFunction = (item) => item.id;
    ({ container } = render(<GenericList data={someData} itemComponent={itemComponent} getKeyFunction={getKeyFunction} />));
  });

  it('should render component without crashing', () => {
    expect(container.firstChild).toBeInTheDocument();
  });

  it('should have as many children as data elements are given', () => {
    expect(container.firstChild!.childNodes.length).toEqual(someData.length);
  });

  it('should render data elements according to the given itemComponent', () => {
    for (let i = 0; i < someData.length; ++i) {
      expect(container.firstElementChild!.querySelectorAll('p')
        .item(i).innerHTML)
        .toEqual(someData[i].name);
    }
  });
});
