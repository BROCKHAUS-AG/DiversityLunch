export function shallowEquals<T>(a: T, b: T): boolean {
    if (typeof a !== typeof b) {
        return false;
    }
    if (typeof a !== 'object' || a === null) {
        return a === b;
    }
    const keysA = Object.keys(a);
    const keysB = Object.keys(b);
    if (keysA.length !== keysB.length) {
        return false;
    }
    // @ts-ignore
    return keysA.every((key) => a[key] === b[key]);
}
