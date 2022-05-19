export type DropdownOption<T> = {
    // real value
    value: T,
    // shown value
    label: string,
};

export type DropdownOptions<T> = DropdownOption<T>[];
